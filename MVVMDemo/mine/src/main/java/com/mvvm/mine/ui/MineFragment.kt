package com.cniao5.mine.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.findNavController
import com.alibaba.android.arouter.launcher.ARouter
import com.mvvm.common.base.BaseFragment
import com.mvvm.mine.R
import com.mvvm.mine.databinding.FragmentMineBinding
import com.mvvm.service.repo.CniaoDbHelper
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *
 * ----------------------------------------------------------------
 */
class MineFragment : BaseFragment() {

    private val viewModel: MineViewModel by viewModel()

    override fun getLayoutRes() = R.layout.fragment_mine

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentMineBinding.bind(view).apply {
            vm = viewModel
            //UI操作 登出
            btnLogoutMine.setOnClickListener {
                CniaoDbHelper.deleteUserInfo(requireContext())
                ARouter.getInstance().build("/login/login").navigation()
            }


            //跳转userInfoFragment
            ivUserIconMine.setOnClickListener {
                val info = viewModel.liveInfo.value
                if (info == null) {
                    ARouter.getInstance().build("/login/login").navigation()
                } else {
                    info.company = "自由职业者"
                    val action = MineFragmentDirections
                        .actionMineFragmentToUserInfoFragment(info)
                    findNavController().navigate(action)
                }
            }
        }
    }


    override fun initData() {
        super.initData()
        CniaoDbHelper.getLiveUserInfo(requireContext()).observeKt {
            viewModel.getUserInfo(it?.token)
        }
    }
}