package com.mvvm.common.retrofit.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.LogUtils
import com.lbw.retrofitdemo.CNService
import com.lbw.retrofitdemo.LoginReq
import com.lbw.retrofitdemo.retrofit.KtRetrofit
import com.lbw.retrofitdemo.retrofit.model.ApiResponse
import com.lbw.retrofitdemo.retrofit.model.Empty
import com.lbw.retrofitdemo.retrofit.model.Error
import com.lbw.retrofitdemo.retrofit.model.Success
import com.lbw.retrofitdemo.retrofit.support.serverRsp
import com.lbw.retrofitdemo.retrofit.support.toLiveData
import com.mvvm.common.R
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit

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