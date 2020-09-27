package com.mvvm.home

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.mvvm.common.base.BaseFragment
import com.mvvm.home.databinding.FragmentHomeBinding

/**
 * @creater：lbw
 * @time：20/9/27 16:17
 * @desc：首页fragment
 */
class HomeFragment : BaseFragment() {


    override fun getLayoutRes(): Int = R.layout.fragment_home

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentHomeBinding.bind(view)
    }
}