package com.mvvm.mvvmdemo

import com.mvvm.common.BaseApplication
import com.mvvm.common.ktx.application
import com.mvvm.service.assistant.AssistantApp

/**
 * Application的声明类
 */
class CnApplication : BaseApplication() {


    override fun initConfig() {
        super.initConfig()

        //doKit调试工具的初始化配置
        AssistantApp.initConfig(application)
    }


}