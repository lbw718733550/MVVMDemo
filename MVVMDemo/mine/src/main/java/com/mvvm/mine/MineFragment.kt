package com.mvvm.mine

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.alibaba.android.arouter.launcher.ARouter
import com.mvvm.common.base.BaseFragment
import com.mvvm.mine.databinding.FragmentMineBinding
import com.mvvm.service.repo.CniaoDbHelper
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @creater：lbw
 * @time：20/9/27 16:22
 * @desc：
 */
class MineFragment : BaseFragment() {

    private val viewModel: MineViewModel by viewModel()

    override fun getLayoutRes(): Int = R.layout.fragment_mine

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentMineBinding.bind(view).apply {
            vm = viewModel
            //UI操作 登出
            btnLogoutMine.setOnClickListener {
                //清楚本地报错的用户信息
                CniaoDbHelper.deleteUserInfo(requireContext())
                //回到登录界面
                ARouter.getInstance().build("/login/login").navigation()
            }
        }
    }

    override fun initData() {
        super.initData()
        CniaoDbHelper.getLiveUserInfo(requireContext()).observerKt {
            viewModel.liveUser.value = it
        }
    }

}