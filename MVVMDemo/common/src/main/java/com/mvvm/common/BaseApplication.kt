package com.mvvm.common

import android.app.Application
import com.blankj.utilcode.util.LogUtils
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * 创建者：lbw
 * 时间：21:21 20/9/16
 * 描述：抽象的公用BaseApplication
 */
abstract class BaseApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)//目前已知bug，除了level.error外，使用androidlogger会导致崩溃
            //context
            androidContext(this@BaseApplication)
            //依赖注入模块
//            modules()
        }
        initConfig()
        initData()

        LogUtils.d("BaseApplication onCreate")
    }

    /**
     * 可用于必要的配置初始化
     */
    protected open fun initConfig() {}

    /**
     * 可用于子类实现必要的数据初始化
     */
    protected open fun initData() {}
}