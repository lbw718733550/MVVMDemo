package com.mvvm.common.base.adapter.variety

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.common.R

/**
 * 一个代理类的实例通常长这个样子：
 */
class VarietyTest : Proxy<Text, TextViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = parent.context.run {
            TextView(this)
        }
        // 构建表项ViewHolder
        return TextViewHolder(itemView)

    }

    override fun onBindViewHolder(
        holder: TextViewHolder,
        data: Text,
        index: Int,
        action: ((Any?) -> Unit)?
    ) {

    }
}


// 与文字类表项对应的“文字数据”
data class Text( var text: String )

// 文字类表项ViewHolder
class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//    val tvName = itemView.findViewById<TextView>(R.id.tvName)
}


//// 构建适配器
//val varietyAdapter = VarietyAdapter().apply {
//    // 为Adapter添加两种代理，分别显示文字和图片
//    addProxy(TextProxy())
//    addProxy(ImageProxy())
//    // 构建数据（不同数据类型融合在一个列表中）
//    dataList = mutableListOf(
//        Text("item 1"), // 代表文字表项
//        Image("#00ff00"), //代表图片表项
//        Text("item 2"),
//        Text("item 3"),
//        Image("#88ff00")
//    )
//    notifyDataSetChanged()
//}
//// 将Adapter赋值给RecyclerView
//recyclerView?.adapter = varietyAdapter
//recyclerView?.layoutManager = LinearLayoutManager(this)
