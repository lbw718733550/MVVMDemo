package com.mvvm.common.base.adapter.variety

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * 类型无关适配器
 * VarietyAdapter的声明避开了所有和类型相关的信息：
 * *原本RecyclerView.Adapter的子类必须声明一个具体的ViewHolder类型，这里直接使用了RecyclerView.ViewHolder基类。
 * *原本RecyclerView.Adapter中的datas必须是一个存放具体数据类型的列表，这里直接使用了所有非空类型的基类Any。
 */
class VarietyAdapter(
    var data: MutableList<Any> = mutableListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    // 构建表项布局
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    // 填充表项内容
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    // 获取表项数量
    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


}