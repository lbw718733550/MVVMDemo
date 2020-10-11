package com.mvvm.login

import androidx.activity.viewModels
import com.mvvm.common.base.BaseActivity
import com.mvvm.login.databinding.ActivityLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * 创建者：lbw
 * 时间：16:46 20/10/11
 * 描述：登录界面
 */
class LoginActivity : BaseActivity<ActivityLoginBinding>(){

    private val viewModel: LoginViewModel by viewModels { defaultViewModelProviderFactory }

    override fun getLayoutRes(): Int = R.layout.activity_login


    override fun initView() {
        super.initView()
        mBinding?.apply {
            vm = viewModel
        }
    }

    override fun initConfig() {
        super.initConfig()
    }

    override fun initData() {
        super.initData()
    }
}