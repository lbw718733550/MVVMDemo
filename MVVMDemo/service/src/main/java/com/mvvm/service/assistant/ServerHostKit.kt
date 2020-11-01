package com.mvvm.service.assistant

import android.app.AlertDialog
import android.content.Context
import com.blankj.utilcode.util.ToastUtils
import com.didichuxing.doraemonkit.kit.AbstractKit
import com.mvvm.common.utils.*
import com.mvvm.service.R


/**
 * 用于配置切换不同接口host，调试工具
 */
class ServerHostKit : AbstractKit() {


    override val icon: Int = R.drawable.icon_server_host

    override val name: Int = R.string.str_server_host_dokit

    override fun onAppInit(context: Context?) {

    }

    private val hostMap = mapOf<String, String>(
        "开发环境Host" to HOST_DEV,
        "QA测试Host" to HOST_QA,
        "线上正式Host" to HOST_PRODUCT,
    )
    private val hosts = hostMap.values.toTypedArray()
    private val names = hostMap.keys.toTypedArray()


    override fun onClick(context: Context?) {
        val pos = hostMap.values.indexOf(getBaseHost()) % hostMap.size
        //弹窗，用于显示很选择不同的host配置
        context ?: return ToastUtils.showShort("~~ context null ~~")
        AlertDialog.Builder(context)
            .setSingleChoiceItems(names, pos){ dialog, which ->
                setBaseHost(hosts[which])
                dialog.dismiss()
            }.show()
    }


}