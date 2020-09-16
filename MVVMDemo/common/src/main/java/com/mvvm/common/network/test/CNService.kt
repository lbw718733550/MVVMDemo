package com.lbw.retrofitdemo

import androidx.lifecycle.LiveData
import com.lbw.retrofitdemo.retrofit.model.ApiResponse
import com.lbw.retrofitdemo.retrofit.model.NetResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * @creater：lbw
 * @time：20/9/10 16:43
 * @desc：
 */
interface CNService {

    @POST("accounts/course/10301/login")
    fun login(@Body body: LoginReq): Call<NetResponse>

    @GET("member/userinfo")
    fun userInfo(): Call<NetResponse>

    @GET("member/userinfo")
    fun userInfo2(): LiveData<ApiResponse<NetResponse>>


}