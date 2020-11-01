package com.mvvm.mine

import com.cniao5.mine.net.MineService
import com.cniao5.mine.repo.IMineResource
import com.cniao5.mine.repo.MineRepo
import com.cniao5.mine.ui.MineViewModel
import com.mvvm.common.utils.getBaseHost
import com.mvvm.retrofitdemo.retrofit.KtRetrofit
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * koin的 mine module
 */

val moduleMine = module {

    //service retrofit
    single {
        get<KtRetrofit> { parametersOf(getBaseHost()) }.getService(MineService::class.java)
    }

    //repo LoginResource

    single { MineRepo(get()) } bind IMineResource::class

    viewModel { MineViewModel(get()) }
}
