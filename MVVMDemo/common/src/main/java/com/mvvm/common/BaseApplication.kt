package com.mvvm.common

import android.app.Application
import com.blankj.utilcode.util.LogUtils
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
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
        /** 启动koin注入框架的初始化，使用modules加载依赖注入模块，也可以在外面用loadKoinModules()加载，
            可以load就可以在外面声明，所以每个module可以申请自己的koin的模块注入*/
        startKoin {
            androidLogger(Level.ERROR)//目前已知bug，除了level.error外，使用androidlogger会导致崩溃
            //context
            androidContext(this@BaseApplication)
            //依赖注入模块
//            modules()
        }
        //依赖注入模块
//        loadKoinModules()
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