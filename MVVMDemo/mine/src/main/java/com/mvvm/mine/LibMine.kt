package com.mvvm.mine

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * koin的 mine module
 */

val moduleMine = module {

    viewModel { MineViewModel() }
}
