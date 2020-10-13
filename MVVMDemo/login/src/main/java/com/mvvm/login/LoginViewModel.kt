package com.mvvm.login

import android.content.Context
import android.view.View
import androidx.databinding.ObservableField
import com.blankj.utilcode.util.ToastUtils
import com.cniao5.login.net.LoginReqBody
import com.mvvm.common.base.BaseViewModel
import com.mvvm.login.repo.ILoginResource

/**
 * 创建者：lbw
 * 时间：16:51 20/10/11
 * 描述：登录界面逻辑的viewModel
 */
class LoginViewModel(private val resource: ILoginResource): BaseViewModel() {

    var obMobile = ObservableField<String>()
    var obPassword = ObservableField<String>()

    val liveRegisterRsp = resource.registerRsp
    val liveLoginRsp = resource.loginRsp


    /**
     * 检查是否注册的账号
     */
    private fun checkRegister(mobi: String) = serverAwait {
        resource.checkRegister(mobi)
    }

    /**
     * 调用登录
     * val mobi: String = "18648957777",
     * val password: String = "cn5123456"
     */
    internal fun repoLogin(){
        val account = obMobile.get() ?: return
        val password = obPassword.get() ?: return
        serverAwait {
            resource.requestLogin(LoginReqBody(account, password))
        }
    }

    fun goLogin(){
        val account = obMobile.get() ?: return
        checkRegister(account)
    }


    fun wechat(ctx: Context) {
        ToastUtils.showShort("点击了微信登录")
    }

    fun qq(v: View) {
        ToastUtils.showShort("点击了QQ登录")
    }

    fun weibo() {
        ToastUtils.showShort("点击了微博登录")
    }

    fun AA(view: View) {
        ToastUtils.showShort("静态点击方式")
    }


}