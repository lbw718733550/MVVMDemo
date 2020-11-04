package com.mvvm.study

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.mvvm.common.base.BaseFragment
import com.mvvm.service.repo.CniaoDbHelper
import com.mvvm.study.databinding.FragmentStudyBinding
import com.mvvm.study.ui.StudyLoadStateAdapter
import com.mvvm.study.ui.StudyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @creater：lbw
 * @time：20/9/27 16:20
 * @desc：学习 frametn
 */
class StudyFragment : BaseFragment()  {

    private val viewModel: StudyViewModel by viewModel()


    override fun getLayoutRes(): Int = R.layout.fragment_study

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentStudyBinding.bind(view).apply {
            vm = viewModel
        }
    }

    override fun initData() {
        super.initData()
        //观察数据库中的userInfo
        CniaoDbHelper.getLiveUserInfo(requireContext())
            .observeKt {
                viewModel.obUserInfo.set(it)
            }

        //获取到最近学习的数据列表
        viewModel.apply {
            adapter.withLoadStateFooter(footer = StudyLoadStateAdapter())


        }



    }




}