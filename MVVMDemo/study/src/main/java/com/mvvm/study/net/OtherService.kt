package com.cniao5.study.net

import com.mvvm.service.network.BaseRsp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
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
interface OtherService {


    //region 首页用的 页面配置
    /**
     * 获取模块组件列表
     */
    @GET("/allocation/component/list")
    fun getModuleList(@Query("module_id") moduleId: Int): Call<BaseRsp>

    /**
     * 获取页面列表
     */
    @GET("/allocation/page/list")
    fun getPageList(): Call<BaseRsp>

    /**
     * 根据页面id 获取页面的模块列表
     */
    @GET("/allocation/module/list")
    fun getPageModuleList(@Query("page_id") pageId: Int): Call<BaseRsp>

    //endregion

    /**
     * 根据课程key，获取播放地址
     */
    @GET("/lesson/play/v2")
    fun getCoursePlayUrl(@Query("key") key: String): Call<BaseRsp>


    /**
     * banner列表
     * [type]类型 1:小程序 2:web 3:h5 4:ios 5:android 如: 2表示web 默认2
     * [show]页面显示 1 首页 2 课程 3 大数据学院 4 机器人学院 5 人工智能学院 6 推广员 默认1
     * @Query("type") type: Int=5, @Query("page_show") show: Int 不传参了，现在据首页有数据，且type = web
     */
    @GET("/ad/new/banner/list")
    fun getBannerList(): Call<BaseRsp>


    /**
     * 获取老师列表
     */
    @GET("/teacher/list")
    fun getTeacherList(): Call<BaseRsp>

    /**
     * 根据teacher的id 获取老师的课程
     */
    @GET("/teacher/courses")
    fun getTeacherCourseList(@Query("id") id: Int): Call<BaseRsp>

    /**
     * 根据teacher的id 获取老师的基本信息
     */
    @GET("/teacher/detail")
    fun getTeacherInfo(@Query("id") id: Int): Call<BaseRsp>


    /**
     * 根据course_id 查询当前学员是否有课程，班级的权限
     */
    @GET("/course/authority")
    fun getCoursePermission(@Query("course_id") courseId: Int): Call<BaseRsp>


    /**
     * 收藏/取消收藏课程
     */
    @POST("/course/favorites")
    fun postFavorites(@Body body: FavoriteReq): Call<BaseRsp>


    /**
     * 根据course_id 查询相关推荐
     */
    @GET("/course/related/recommend")
    fun getRecommend(@Query("course_id") courseId: Int): Call<BaseRsp>


    /**
     * 根据course_id 查询课程章节
     */
    @GET("/course/chapter")
    fun getCourseChapter(@Query("course_id") courseId: Int): Call<BaseRsp>


    /**
     * 根据course_id 查询课程详情
     */
    @GET("/course/detail")
    fun getCourseInfo(@Query("course_id") courseId: Int): Call<BaseRsp>

    /**
     * 根据course_id查询对应的课程评论
     */
    @GET("/comment/list")
    fun getCourseCommentList(
        @Query("course_id") courseId: Int,
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10,
    ): Call<BaseRsp>


}