package com.mvvm.mine

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.mvvm.common.base.BaseFragment
import com.mvvm.mine.databinding.FragmentMineBinding

/**
 * @creater：lbw
 * @time：20/9/27 16:22
 * @desc：
 */
class MineFragment : BaseFragment() {


    override fun getLayoutRes(): Int = R.layout.fragment_mine

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentMineBinding.bind(view)
    }

}