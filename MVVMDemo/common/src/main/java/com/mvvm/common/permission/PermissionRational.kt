package com.mvvm.common.permission

import java.lang.annotation.ElementType

/**
 * 权限需不需要弹窗
 *
 */

@Target(AnnotationTarget.FUNCTION) //作用域只限于函数
annotation class  PermissionRational(
    val value: Int
)