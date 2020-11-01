package com.mvvm.service

import com.mvvm.retrofitdemo.retrofit.KtRetrofit
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Service模块相关的koin的module配置
 */
val moduleService: Module = module {

    /** 配置接口的host */
    single<KtRetrofit> { (host: String) -> KtRetrofit.initConfig(host)}


}
