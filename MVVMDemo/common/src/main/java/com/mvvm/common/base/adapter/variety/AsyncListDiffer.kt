package com.mvvm.common.base.adapter.variety

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import kotlinx.coroutines.*

/**
 * 通过后台线程中的[DiffUtil]计算两个列表之间的差异的助手
 * androidx.recyclerview.widget包下已经有一个可直接使用的AsyncListDiffer,可惜的是它和类型绑定，无法和无类型适配器一起使用。
 * 所以这里重新写一个
 */
class AsyncListDiffer(
    /**  之所以使用[listUpdateCallback]，目的是让AsyncListDiffer的适用范围不局限于RecyclerView.Adapter */
    var listUpdateCallback: ListUpdateCallback,
    /**  自定义协程的调度器[CoroutineDispatcher]，用于适配既有代码，把比对逻辑放到既有线程中，而不是新起一个
    常见用法是通过[asCoroutineDispatcher]将现有的[Executor]转换为[CoroutineDispatcher] */
    dispatcher: CoroutineDispatcher
) : DiffUtil.Callback(), CoroutineScope by CoroutineScope(SupervisorJob() + dispatcher) {
    /**  可装填任何类型的新旧列表  */
    var oldList = listOf<Any>()
    var newList = listOf<Any>()

    /**
     * 用于标记每一次提交列表
     * 由[submitList]调用的时间自动增加
     */
    private var maxSubmitGeneration: Int = 0

    /**  提交新列表,然后在后台线程中与旧列表进行比较,比较完成后，结果将分派到[ListUpdateCallback] */
    fun submitList(newList: List<Any>) {
        val submitGeneration = ++maxSubmitGeneration
        this.newList = newList
        //快速返回：没有需要更新的东西
        if (this.oldList == newList) return

        // 快速返回：旧列表为空，全量接收新列表
        if (this.oldList.isEmpty()) {
            this.oldList = newList
            //保存列表最新数据的快照
            oldList = newList.toList()
            listUpdateCallback.onInserted(0, newList.size)
            return
        }

        // 启动协程比对数据
        launch {
            val diffResult = DiffUtil.calculateDiff(this@AsyncListDiffer)
            //保存列表最新数据的快照
            oldList = newList.toList()
            // 将比对结果抛到主线程并应用到ListUpdateCallback接口
            withContext(Dispatchers.Main) {
                // 只保留最后一次提交的比对结果，其他的都被丢弃
                if (submitGeneration == maxSubmitGeneration) {
                    diffResult.dispatchUpdatesTo(listUpdateCallback)
                }
            }
        }
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        // using existing hashCode() to make sure whether new and old items are one object
        return oldItem.hashCode() == newItem.hashCode()
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        // using existing hashCode() to make sure whether new and old items have the same content
        return oldItem == newItem
    }
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val oldItem = oldList[oldItemPosition] as? Diff
        val newItem = newList[newItemPosition] as? Diff
        if (oldItem == null || newItem == null) return null
        // if new and old items are the same object but have different content, call diff() to find the precise difference
        return oldItem diff newItem
    }
}

/**
 * an interface should be implemented by object wanna be differentiated by [AsyncListDiffer]
 */
interface Diff {
    /**
     * diff one object to [other] object
     * @return the detail of difference defined by yourself
     */
    infix fun diff(other: Any?): Any?
}