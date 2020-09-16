package com.lbw.retrofitdemo.retrofit.model

import retrofit2.Response

/**
 * 创建者：lbw
 * 时间：21:52 20/9/7
 * 描述：密封类形式的，网络数据返回封装类
 */
sealed class ApiResponse<T> {

    companion object{
        fun<T> create(response: Response<T>): ApiResponse<T>{
            return if(response.isSuccessful){
                val body = response.body()
                if (body == null || response.code() == 204) {
                    Empty()
                }else{
                    Success(body)
                }
            }else{
                Error(response.code(), response.errorBody()?.string()?:response.message())
            }
        }

        fun<T> create(errorCode: Int, error: Throwable): Error<T>{
            return Error(errorCode, error.message?: "Unkonw Error")
        }
    }

}

/** 空数据  */
class Empty<T>(): ApiResponse<T>()
/** 成功  */
data class Success<T>(val data: T): ApiResponse<T>()
/** 失败  */
data class Error<T>(val errorCode: Int, val errorMsg: String): ApiResponse<T>()

internal const val UNKNOWN_ERROR_CODE = -1//未知错误码