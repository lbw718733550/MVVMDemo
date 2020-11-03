package com.mvvm.study.repo

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.cniao5.study.net.BoughtRsp
import com.cniao5.study.net.StudiedRsp
import com.cniao5.study.net.StudyInfoRsp
import kotlinx.coroutines.flow.Flow

/**
 * 创建者：lbw
 * 时间：7:02 20/11/4
 * 描述：
 */
interface IStudyResource {

    val liveStudyInfo: LiveData<StudyInfoRsp>

    /**
     * 学习情况
     */
    suspend fun getStudyInfo()


    /**
     * 最近学习列表
     */
    suspend fun getStudyList(): Flow<PagingData<StudiedRsp.Data>>


    /**
     * 购买的课程
     */
    suspend fun getBoughtCourse(): Flow<PagingData<BoughtRsp.Data>>


}