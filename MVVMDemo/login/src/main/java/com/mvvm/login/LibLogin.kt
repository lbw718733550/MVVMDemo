package com.mvvm.login

import com.mvvm.common.utils.getBaseHost
import com.mvvm.login.net.LoginService
import com.mvvm.login.repo.ILoginResource
import com.mvvm.login.repo.LoginRepo
import com.mvvm.retrofitdemo.retrofit.KtRetrofit
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.parameter.parametersOf
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * koin
 */

val moduleLogin = module {

    //service retrofit
    single {
        get<KtRetrofit> { parametersOf(getBaseHost()) }.getService(LoginService::class.java)
    }

    //repo LoginResource
    single { LoginRepo(get()) } bind ILoginResource::class

    //viewModel
    viewModel { LoginViewModel(get()) }
}


