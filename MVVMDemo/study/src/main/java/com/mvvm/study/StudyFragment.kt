package com.mvvm.study

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.mvvm.common.base.BaseFragment
import com.mvvm.study.databinding.FragmentStudyBinding

/**
 * @creater：lbw
 * @time：20/9/27 16:20
 * @desc：学习 frametn
 */
class StudyFragment : BaseFragment()  {

    override fun getLayoutRes(): Int = R.layout.fragment_study

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentStudyBinding.bind(view)
    }




}