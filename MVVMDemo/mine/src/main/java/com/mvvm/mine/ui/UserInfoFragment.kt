package com.cniao5.mine.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mvvm.common.base.BaseFragment
import com.mvvm.mine.R
import com.mvvm.mine.databinding.FragmentUserInfoBinding

/**
 * 作者： 志威  zhiwei.org
 * 主页： Github: https://github.com/zhiwei1990
 * 日期： 2020年10月19日 11:13
 * 签名： 天行健，君子以自强不息；地势坤，君子以厚德载物。
 *      _              _           _     _   ____  _             _ _
 *     / \   _ __   __| |_ __ ___ (_) __| | / ___|| |_ _   _  __| (_) ___
 *    / _ \ | '_ \ / _` | '__/ _ \| |/ _` | \___ \| __| | | |/ _` | |/ _ \
 *   / ___ \| | | | (_| | | | (_) | | (_| |  ___) | |_| |_| | (_| | | (_) |
 *  /_/   \_\_| |_|\__,_|_|  \___/|_|\__,_| |____/ \__|\__,_|\__,_|_|\___/  -- 志威 zhiwei.org
 *
 * You never know what you can do until you try !
 * ----------------------------------------------------------------
 * 用户信息界面Fragment
 */
class UserInfoFragment : BaseFragment() {

    private val args by navArgs<UserInfoFragmentArgs>()

    override fun getLayoutRes() = R.layout.fragment_user_info

    override fun bindView(view: View, savedInstanceState: Bundle?) =
        FragmentUserInfoBinding.bind(view).apply {
            //toolbar返回
            toolbarUserInfo.setNavigationOnClickListener { findNavController().navigateUp() }
//            toolbarUserInfo.setupWithNavController(findNavController())
//            toolbarUserInfo.navigationIcon?.setTint(Color.WHITE)
            // save 返回
            btnSaveUserInfo.setOnClickListener { findNavController().navigateUp() }
            //
            info = args.info
        }

}