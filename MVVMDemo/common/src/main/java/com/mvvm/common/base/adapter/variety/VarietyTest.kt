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
