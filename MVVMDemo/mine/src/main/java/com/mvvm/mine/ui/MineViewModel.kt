package com.cniao5.mine.ui

import com.cniao5.mine.repo.IMineResource
import com.mvvm.common.base.BaseViewModel

/**
 *
 * Mine模块的viewModel
 */
class MineViewModel(private val repo: IMineResource) : BaseViewModel() {

    //用在userInfoFragment中
    val liveInfo = repo.liveUserInfo

    /**
     * 获取用户信息
     */
    fun getUserInfo(token: String?) = serverAwait {
        token?.let {
            repo.getUserInfo(token)
        }
    }

}