package com.mvvm.login.repo

import androidx.lifecycle.LiveData
import com.blankj.utilcode.util.LogUtils
import com.cniao5.login.net.LoginReqBody
import com.cniao5.login.net.LoginRsp
import com.cniao5.login.net.RegisterRsp
import com.mvvm.common.model.SingleLiveData
import com.mvvm.login.net.LoginService
import com.mvvm.retrofitdemo.retrofit.support.serverData
import com.mvvm.service.network.onBizError
import com.mvvm.service.network.onBizOk
import com.mvvm.service.network.onFailure
import com.mvvm.service.network.onSuccess

/**
 * 登录模块数据仓库
 */
class LoginRepo(private val service: LoginService) : ILoginResource {

    private val _registerRsp = SingleLiveData<RegisterRsp>()
    private val _loginRsp = SingleLiveData<LoginRsp>()

    override val registerRsp: LiveData<RegisterRsp> = _registerRsp
    override val loginRsp: LiveData<LoginRsp> = _loginRsp

    override suspend fun checkRegister(mobi: String) {
        service.isRegister(mobi)
            .serverData()
            .onSuccess {
                onBizError { code, message ->
                    LogUtils.w("是否注册 BizError $code,$message")
                }
                onBizOk<RegisterRsp> { code, data, message ->
                    _registerRsp.value = data
                    LogUtils.i("是否注册 BizOK $data")
                    return@onBizOk
                }
            }.onFailure {
                LogUtils.e("是否注册 接口异常 ${it.message}")
            }

    }

    override suspend fun requestLogin(body: LoginReqBody) {
        service.login(body)
            .serverData()
            .onSuccess {
                onBizError { code, message ->
                    LogUtils.w("登录接口 BizError $code,$message")
                }
                onBizOk<LoginRsp> { code, data, message ->
                    _loginRsp.value = data
                    LogUtils.i("登录接口 BizOK $data")
                    return@onBizOk
                }
            }.onFailure {
                LogUtils.e("登录接口 异常 ${it.message}")
            }

    }


}