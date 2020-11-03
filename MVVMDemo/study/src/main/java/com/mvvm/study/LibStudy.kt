package com.mvvm.study

import com.cniao5.study.net.StudyService
import com.mvvm.common.utils.getBaseHost
import com.mvvm.retrofitdemo.retrofit.KtRetrofit
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

/**
 * 学习中心 module koin配置
 */

var modelStudy = module{

    single {
        get<KtRetrofit>{parametersOf(getBaseHost())}.getService(StudyService::class.java)
    }




}

