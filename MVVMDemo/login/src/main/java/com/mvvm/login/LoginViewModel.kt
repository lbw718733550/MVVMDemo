package com.mvvm.login

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

/**
 * 创建者：lbw
 * 时间：16:51 20/10/11
 * 描述：登录界面逻辑的viewModel
 */
class LoginViewModel: ViewModel() {

    var obMobile = ObservableField<String>()
    var obPassword = ObservableField<String>()



    fun goLogin(){

    }


}