package com.mvvm.login

import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.ToastUtils
import com.cniao5.login.net.RegisterRsp
import com.mvvm.common.base.BaseActivity
import com.mvvm.common.ktx.context
import com.mvvm.login.databinding.ActivityLoginBinding
import com.mvvm.service.repo.CniaoDbHelper
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * 创建者：lbw
 * 时间：16:46 20/10/11
 * 描述：登录界面
 */
@Route(path = "/login/login")
class LoginActivity : BaseActivity<ActivityLoginBinding>(){

    private val viewModel: LoginViewModel by viewModel()

    override fun getLayoutRes(): Int = R.layout.activity_login


    override fun initView() {
        super.initView()
        mBinding?.apply {
            vm = viewModel
            //点击事件
            mtoolbarLogin.setNavigationOnClickListener { finish() }
            tvRegisterLogin.setOnClickListener {
                ToastUtils.showShort("当前课程项目未实现注册账号功能!")
            }
        }
    }

    override fun initConfig() {
        super.initConfig()
        viewModel.apply {

            liveRegisterRsp.observeKt {
                if (it?.is_register == RegisterRsp.FLAG_IS_REGISTERED) {
                    repoLogin()
                }
            }
            liveLoginRsp.observeKt {rsp ->
                ToastUtils.showShort("登录结果 " + rsp.toString())
                rsp?.let {
                    CniaoDbHelper.insertUserInfo(context, rsp)
                }
                finish()
            }
        }
    }

    override fun initData() {
        super.initData()
    }





}