package com.mvvm.retrofitdemo.retrofit.support

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mvvm.retrofitdemo.retrofit.model.ApiResponse
import com.mvvm.retrofitdemo.retrofit.model.DataResult
import com.mvvm.retrofitdemo.retrofit.model.UNKNOWN_ERROR_CODE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.await
import retrofit2.awaitResponse

/**
 * retrofit 的 Response的扩展函数，属性
 */




//region retrofit 相关扩展


/**
 * Retrofit的Call执行异步，并转化为liveData可观察结果
 */
fun <T : Any> Call<T>.toLiveData(): LiveData<T?> {
    val live = MutableLiveData<T>()
    this.enqueue(object : Callback<T> {
        override fun onFailure(call: Call<T>, t: Throwable) {
            live.postValue(null)
        }

        override fun onResponse(call: Call<T>, response: retrofit2.Response<T>) {
            val value = if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
            live.postValue(value)
        }
    })
    return live
}

/**
 * 扩展retrofit的返回数据，调用await，并catch超时等异常
 * @return DataResult 返回格式为ApiResponse封装
 */
suspend fun <T : Any> Call<T>.serverData(): DataResult<T> {
    var result: DataResult<T> = DataResult.Loading
    kotlin.runCatching {
        this.await()
    }.onFailure {
        result = DataResult.Error(RuntimeException(it))
        it.printStackTrace()
    }.onSuccess {
        result = DataResult.Success(it)
    }
    return result
}


/**
 * 扩展retrofit的返回数据，调用await，并catch超时等异常
 * @return ApiResponse 返回格式为ApiResponse封装
 */
suspend fun <T : Any> Call<T>.serverRsp(): ApiResponse<T> {
    var result: ApiResponse<T>
    val response = kotlin.runCatching {
        this.awaitResponse()
    }.onFailure {
        result = ApiResponse.create(UNKNOWN_ERROR_CODE, it)
        it.printStackTrace()
    }.getOrThrow()
        result = ApiResponse.create(response)
    return result
}

//endregion
