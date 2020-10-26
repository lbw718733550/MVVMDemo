package com.mvvm.common.permission

import java.lang.annotation.ElementType

/**
 * 权限请求的成功
 */

@Target(AnnotationTarget.FUNCTION) //作用域只限于函数
annotation class  PermissionGrant(
    val value: Int
)