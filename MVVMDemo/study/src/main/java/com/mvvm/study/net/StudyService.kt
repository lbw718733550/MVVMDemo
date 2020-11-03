package com.cniao5.study.net

import com.mvvm.service.network.BaseRsp
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 作者： 志威  zhiwei.org
 * 主页： Github: https://github.com/zhiwei1990
 * 日期： 2020年04月27日 14:51
 * 签名： 天行健，君子以自强不息；地势坤，君子以厚德载物。
 *      _              _           _     _   ____  _             _ _
 *     / \   _ __   __| |_ __ ___ (_) __| | / ___|| |_ _   _  __| (_) ___
 *    / _ \ | '_ \ / _` | '__/ _ \| |/ _` | \___ \| __| | | |/ _` | |/ _ \
 *   / ___ \| | | | (_| | | | (_) | | (_| |  ___) | |_| |_| | (_| | | (_) |
 *  /_/   \_\_| |_|\__,_|_|  \___/|_|\__,_| |____/ \__|\__,_|\__,_|_|\___/  -- 志威 zhiwei.org
 *
 * You never know what you can do until you try !
 * ----------------------------------------------------------------
 *
 */
interface StudyService {


    /**
     * 用户学习详情
     */
    @GET("/member/study/info")
    fun getStudyInfo(): Call<BaseRsp>


    /**
     * 用户学习过的课程列表
     */
    @GET("/member/courses/studied")
    fun getStudyList(
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10
    ): Call<BaseRsp>


    /**
     * 用户购买的课程
     */
    @GET("/member/courses/bought")
    fun getBoughtCourse(
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10
    ): Call<BaseRsp>

}