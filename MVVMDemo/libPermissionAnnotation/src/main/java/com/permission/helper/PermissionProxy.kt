package com.permission.helper


/** 接口 */
interface PermissionProxy<T> {

    /**
     * [source] 类名，因为需要调用类里面打上注解的方法
     */
    fun grant( source: T, permission: Array<String>)

    fun denied( source: T, permission: Array<String>)

    fun rational( source: T, permission: Array<String>) : Boolean

}