package com.mvvm.study.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.mvvm.study.databinding.FooterLoadStateBinding

/**
 * 作者： 志威  zhiwei.org
 * 主页： Github: https://github.com/zhiwei1990
 * 日期： 2020年10月30日 03:20
 * 签名： 天行健，君子以自强不息；地势坤，君子以厚德载物。
 *      _              _           _     _   ____  _             _ _
 *     / \   _ __   __| |_ __ ___ (_) __| | / ___|| |_ _   _  __| (_) ___
 *    / _ \ | '_ \ / _` | '__/ _ \| |/ _` | \___ \| __| | | |/ _` | |/ _ \
 *   / ___ \| | | | (_| | | | (_) | | (_| |  ___) | |_| |_| | (_| | | (_) |
 *  /_/   \_\_| |_|\__,_|_|  \___/|_|\__,_| |____/ \__|\__,_|\__,_|_|\___/  -- 志威 zhiwei.org
 *
 * You never know what you can do until you try !
 * ----------------------------------------------------------------
 */
//paging3 的 footer的viewHolder
class StudyFootLoadVH(
    private val binding: FooterLoadStateBinding,
    private val loadState: LoadState
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(loadState: LoadState) {
        LogUtils.i("footer中 loadState $loadState")
        binding.executePendingBindings()
    }
}

class StudyLoadStateAdapter : LoadStateAdapter<StudyFootLoadVH>() {
    override fun onBindViewHolder(holder: StudyFootLoadVH, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): StudyFootLoadVH {
        return StudyFootLoadVH(
            FooterLoadStateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), loadState
        )
    }

}
