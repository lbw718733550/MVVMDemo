package com.mvvm.service.assistant

import android.app.Application
import com.didichuxing.doraemonkit.DoraemonKit
import com.mvvm.common.BaseApplication


/**
 * 配置dokit的工具类
 */
object  AssistantApp {

    /**
     * 初始化设置
     */
    fun initConfig(application: Application){
        //列表放入各种自定义的kit的集合
        DoraemonKit.install(
            application, mutableListOf(
                ServerHostKit()
            ))
    }


}