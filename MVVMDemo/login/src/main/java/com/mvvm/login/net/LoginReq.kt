package com.cniao5.login.net

import androidx.annotation.Keep

/**
 * 登录模块请求参数
 */

@Keep
data class LoginReqBody(
    val mobi: String,
    val password: String
)