package com.mvvm.common

import android.app.Application
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

        //region koin
        startKoin {
            androidLogger(Level.ERROR)//log level Error方能保证这句话不会报错，要么就不写这个

            androidContext(this@BaseApplication)

//            modules()
        }
        //endregion

    }

}