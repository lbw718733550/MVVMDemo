package com.mvvm.login

import com.mvvm.common.BaseApplication
import com.mvvm.service.moduleServeice
import org.koin.core.context.loadKoinModules

/**
 * @creater：lbw
 * @time：20/10/13 16:08
 * @desc：做为module的时候使用
 */
class LoginApplication : BaseApplication() {

    override fun initConfig() {
        super.initConfig()

        loadKoinModules(moduleServeice)
        loadKoinModules(moduleLogin)
    }

}