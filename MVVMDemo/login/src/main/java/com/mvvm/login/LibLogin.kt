package com.mvvm.login

import com.mvvm.login.net.LoginService
import com.mvvm.login.repo.ILoginResource
import com.mvvm.login.repo.LoginRepo
import com.mvvm.retrofitdemo.retrofit.KtRetrofit
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * koin
 */

val moduleLogin = module {

    //service retrofit
    single {
        KtRetrofit.initConfig("https://course.api.cniao5.com/")
            .getService(LoginService::class.java)
    }

    //repo LoginResource
    single { LoginRepo(get()) } bind ILoginResource::class

    //viewModel
    single { LoginViewModel(get()) }
}


