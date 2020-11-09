package com.mvvm.common.base.adapter.variety

import android.view.ViewGroup
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import java.lang.reflect.ParameterizedType

/**
 * 类型无关适配器
 * VarietyAdapter的声明避开了所有和类型相关的信息：
 * *原本RecyclerView.Adapter的子类必须声明一个具体的ViewHolder类型，这里直接使用了RecyclerView.ViewHolder基类。
 * *原本RecyclerView.Adapter中的datas必须是一个存放具体数据类型的列表，这里直接使用了所有非空类型的基类Any。
 */
class VarietyAdapter(
    // 代理列表
    private var proxyList: MutableList<Proxy<*, *>> = mutableListOf(),
    // 默认在IO共享线程池中执行比对
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) : RecyclerView.Adapter<ViewHolder>() {
    // 构建数据比对器
    private val dataDiffer = AsyncListDiffer(AdapterListUpdateCallback(this), dispatcher)
    // 业务代码通过为dataList赋值实现填充数据
    var dataList: List<Any>
        set(value) {
            // 将填充数据委托给数据比对器
            dataDiffer.submitList(value)
        }
        // 返回上一次比对后的数据快照
        get() = dataDiffer.oldList

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        dataDiffer.cancel() // 当适配器脱离RecyclerView时释放协程资源
    }


    var action: ((Any?) -> Unit)? = null
    // 注入代理
    fun <T, VH : ViewHolder> addProxy(proxy: Proxy<T, VH>) {
        proxyList.add(proxy)
    }
    // 移除代理
    fun <T, VH : ViewHolder> removeProxy(proxy: Proxy<T, VH>) {
        proxyList.remove(proxy)
    }
    // 将构建表项布局分发给代理
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return proxyList[viewType].onCreateViewHolder(parent, viewType)
    }
    // 将填充表项分发给代理
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (proxyList[getItemViewType(position)] as Proxy<Any, ViewHolder>).onBindViewHolder(
            holder,
            dataList[position],
            position,
            action
        )
    }
    // 将填充表项分发给代理（布局刷新）
    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        (proxyList[getItemViewType(position)] as Proxy<Any, ViewHolder>).onBindViewHolder(
            holder,
            dataList[position],
            position,
            action,
            payloads
        )
    }
    // 返回数据总量
    override fun getItemCount(): Int = dataList.size
    // 获取表项类型
    override fun getItemViewType(position: Int): Int {
        return getProxyIndex(dataList[position])
    }
    // 获取代理在列表中的索引
    private fun getProxyIndex(data: Any): Int = proxyList.indexOfFirst {
        // 获取代理类中第一个类型参数的类名
        val firstTypeParamClassName =
            (it.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0].toString()
        // 获取代理类名
        val proxyClassName = it.javaClass.toString()
        // 首要匹配条件：代理类第一个类型参数和数据类型相同
        firstTypeParamClassName == data.javaClass.toString()
                // 次要匹配条件：数据类自定义匹配代理名和当前代理名相同
                && (data as? DataProxyMap)?.toProxy() ?: proxyClassName == proxyClassName
    }

}

/**  抽象代理类 */
abstract class Proxy<T, VH : RecyclerView.ViewHolder> {
    abstract fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    abstract fun onBindViewHolder(holder: VH, data: T, index: Int, action: ((Any?) -> Unit)? = null)
    open fun onBindViewHolder(
        holder: VH,
        data: T,
        index: Int,
        action: ((Any?) -> Unit)? = null,
        payloads: MutableList<Any>
    ) {
        onBindViewHolder(holder, data, index, action)
    }
}


/** 数据和代理的对应关系  */
//有时候服务器返回的列表数据中有type字段，用于指示客户端应该展示哪种布局，即同一个数据类型对应了多种布局方式，VarietyAdapter现有的做法不能满足这个需求，因为匹配规则被写死在getProxyIndex()方法中。
//为了扩展匹配规则，新增接口：
interface DataProxyMap {
    /**  将数据转换成代理类名 */
    fun toProxy(): String
}
//实现方式  让数据类实现这个接口：
//data class Text(
//    var text: String,
//    var type: Int // 用type指定布局类型
//) : VarietyAdapter.DataProxyMap {
//    override fun toProxy(): String {
//        return when (type) {
//            1 -> TextProxy1::class.java.toString() // type为1时对应TextProxy1
//            2 -> TextProxy2::class.java.toString() // type为2时对应TextProxy2
//            else -> TextProxy2::class.java.toString()
//        }
//    }
//}

