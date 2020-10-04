package com.mvvm.common.ktx

import android.app.Application

/**
 *  Application相关拓展
 */

/** 获取application的context，一般用this，这里为了语意，使用application */
val Application.application : Application
    get() = this