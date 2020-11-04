package com.mvvm.study.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.DifferCallback
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.cniao5.study.net.StudiedRsp
import com.mvvm.study.databinding.ItemCourseStudyBinding

/**
 * 创建者：lbw
 * 时间：7:02 20/11/5
 * 描述：
 */
class StudyPageAdapter : PagingDataAdapter<StudiedRsp.Data, StudiedVH>(differCallback) {

    companion object{
        //验证item不一致性
        private val differCallback = object : DiffUtil.ItemCallback<StudiedRsp.Data>(){
            override fun areItemsTheSame(
                oldItem: StudiedRsp.Data,
                newItem: StudiedRsp.Data
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: StudiedRsp.Data,
                newItem: StudiedRsp.Data
            ): Boolean {
                return oldItem == newItem
            }

        }

    }



    override fun onBindViewHolder(holder: StudiedVH, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudiedVH = StudiedVH.createVH(parent)


}


class StudiedVH(private val binding: ItemCourseStudyBinding) :RecyclerView.ViewHolder(binding.root){

    companion object{
        fun createVH(parent: ViewGroup):StudiedVH{
            return StudiedVH(
                ItemCourseStudyBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    fun bind(info: StudiedRsp.Data) {
        binding.info = info
        binding.npbProgressItemStudy.progress = info.progress.toInt()
        binding.executePendingBindings()
    }


}