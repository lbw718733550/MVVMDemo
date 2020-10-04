package com.mvvm.common.network.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.LogUtils
import com.mvvm.retrofitdemo.CNService
import com.mvvm.retrofitdemo.LoginReq
import com.mvvm.retrofitdemo.retrofit.KtRetrofit
import com.mvvm.retrofitdemo.retrofit.model.Empty
import com.mvvm.retrofitdemo.retrofit.model.Error
import com.mvvm.retrofitdemo.retrofit.model.Success
import com.mvvm.retrofitdemo.retrofit.support.serverRsp
import com.mvvm.retrofitdemo.retrofit.support.toLiveData
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        //先获取call，在通过扩展函数call请求获取liveData
        val retrofitCall = KtRetrofit.initConfig("https://course.api.cniao5.com/")
            .getService(CNService::class.java)
            .userInfo()

        val liveInfo = retrofitCall.toLiveData()
        liveInfo.observe(this, Observer {
            LogUtils.d("retrofit userInfo ${it.toString()}")
        })

        val loginCall = KtRetrofit.initConfig("https://course.api.cniao5.com/")
            .getService(CNService::class.java)
            .login(LoginReq())

        //使用LifecycleScope
        lifecycleScope.launch{
            //表达式声明，使用 when，协程，同步的代码形式，执行异步的操作，
            when(val serverRsp = loginCall.serverRsp()){
                is Success -> {
                    LogUtils.i("apiService ${serverRsp.data.toString()}")
                }
                is Empty -> {
                    LogUtils.d("empty apiResponse ")
                }
                is Error -> {
                    LogUtils.e("apiService ${serverRsp.errorMsg}")
                }

            }
        }

        KtRetrofit.initConfig("https://course.api.cniao5.com/")
            .getService(CNService::class.java)
            .userInfo2().observe(this, Observer {
                LogUtils.d("retrofit liveRsp ${it.toString()}")
            })
    }
}