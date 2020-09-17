package com.lbw.retrofitdemo.retrofit.model

import java.lang.Exception

/**
 * 创建者：lbw
 * 时间：21:25 20/9/7
 * 描述：密封类 封装 数据响应
 */
sealed class DataResult<out R> {

    /** 成功状态的时候  */
    data class Success<out T>(val data: T): DataResult<T>()
    /**  返回数据为空的时候 */
    class Empty(): DataResult<Nothing>()
    /** 失败状态的时候  */
    data class Error(val exception: Exception): DataResult<Nothing>()
    /** 数据加载中 */
    object Loading: DataResult<Nothing>()

    override fun toString(): String {
        return when(this){
            is Success<*> -> "Success[data=$data]"
            is Empty -> "Empty[data=]"
            is Error -> "Error[exception=$exception]"
            is Loading -> "Loading"
        }
    }
}

/**
 *  返回结果如果是Success类，且data非null，才认为是成功的。
 */
val DataResult<*>.succeeded get() = this is DataResult.Success && data != null
