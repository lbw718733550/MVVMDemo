package com.mvvm.common.permission

import java.lang.annotation.ElementType

/**
 * 权限请求失败
 */
@Target(AnnotationTarget.FUNCTION) //作用域只限于函数
annotation class  PermissionDenied(
    val value: Int
)