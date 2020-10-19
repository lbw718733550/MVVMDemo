package com.mvvm.mvvmdemo

import com.alibaba.android.arouter.launcher.ARouter
import com.mvvm.common.BaseApplication
import com.mvvm.common.ktx.application
import com.mvvm.login.moduleLogin
import com.mvvm.mine.moduleMine
import com.mvvm.service.assistant.AssistantApp
import com.mvvm.service.moduleService
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module

/**
 * Application的声明类
 */
class CnApplication : BaseApplication() {

    private val modules = arrayListOf<Module>(
        moduleService, /*moduleHome,*/ moduleLogin, moduleMine
    )


    override fun initConfig() {
        super.initConfig()
        //添加common 模块之外的其他模块，组件的koin的modules
        loadKoinModules(modules)
        //doKit调试工具的初始化配置
        AssistantApp.initConfig(application)
        //初始化ARouter路由
        ARouter.init(application)
    }


}