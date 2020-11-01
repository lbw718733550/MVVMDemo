package com.cniao5.mine.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.LogUtils
import com.cniao5.mine.net.MineService
import com.cniao5.mine.net.UserInfoRsp
import com.mvvm.retrofitdemo.retrofit.support.serverData
import com.mvvm.service.network.onBizError
import com.mvvm.service.network.onBizOk
import com.mvvm.service.network.onFailure
import com.mvvm.service.network.onSuccess

/**
 * 作者： 志威  zhiwei.org
 * 主页： Github: https://github.com/zhiwei1990
 * 日期： 2020年10月19日 12:17
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
class MineRepo(private val service: MineService) : IMineResource {

    private val _userInfoRsp = MutableLiveData<UserInfoRsp>()

    override val liveUserInfo: LiveData<UserInfoRsp> = _userInfoRsp

    override suspend fun getUserInfo(token: String?) {
        service.getUserInfo(token)
            .serverData()
            .onSuccess {
                //只要不是接口响应成功，
                onBizError { code, message ->
                    LogUtils.w("获取用户信息 BizError $code,$message")
                }
                onBizOk<UserInfoRsp> { code, data, message ->
                    _userInfoRsp.value = data
                    LogUtils.i("获取用户信息 BizOK $data")
                    return@onBizOk
                }
            }.onFailure {
                LogUtils.e("获取用户信息 接口异常 ${it.message}")
            }
    }

}