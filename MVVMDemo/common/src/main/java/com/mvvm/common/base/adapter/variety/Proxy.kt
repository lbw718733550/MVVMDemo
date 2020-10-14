package com.mvvm.common.base.adapter.variety

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView





/**
 * 抽象代理类，它看上去和RecyclerView.Adapter没什么两样，几乎拥有相同的接口，目的是为了把原本RecyclerView.Adapter做的事情，由它来代理
 * 抽象代理类定义了两个类型参数,第一个T表示表项对应数据的类型，第二个VH表示表项ViewHolder的类型。
 */
abstract class Proxy<T, VH: RecyclerView.ViewHolder> {
    // 构建表项布局
    abstract fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    // 填充表项
    abstract fun onBindViewHolder(holder: VH, data: T, index: Int, action: ((Any?) -> Unit)? = null)
    // 填充表项(局部刷新)
    open fun onBindViewHolder(holder: VH, data: T, index: Int, action: ((Any?) -> Unit)? = null, payloads: MutableList<Any>) {
        onBindViewHolder(holder, data, index, action)
    }

}