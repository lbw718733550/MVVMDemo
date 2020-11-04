package com.mvvm.study.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.blankj.utilcode.util.LogUtils
import com.cniao5.study.net.BoughtRsp
import com.cniao5.study.net.StudiedRsp
import com.cniao5.study.net.StudyInfoRsp
import com.cniao5.study.net.StudyService
import com.mvvm.retrofitdemo.retrofit.support.serverData
import com.mvvm.service.network.onBizError
import com.mvvm.service.network.onBizOk
import com.mvvm.service.network.onFailure
import com.mvvm.service.network.onSuccess
import com.mvvm.study.repo.data.BoughtItemPagingSource
import com.mvvm.study.repo.data.StudiedItemPagingSource
import kotlinx.coroutines.flow.Flow

/**
 * 创建者：lbw
 * 时间：7:14 20/11/4
 * 描述：
 */
class StudyRepo(private val service: StudyService): IStudyResource {

    private val _studyInfo = MutableLiveData<StudyInfoRsp>()
    override val liveStudyInfo: LiveData<StudyInfoRsp> = _studyInfo

    private val pageSize = 10//页码大小

    override suspend fun getStudyInfo() {
        service.getStudyInfo()
            .serverData()
            .onSuccess {
                //只要不是接口响应成功，
                onBizError { code, message ->
                    LogUtils.w("获取学习信息 BizError $code,$message")
                }
                onBizOk<StudyInfoRsp> { code, data, message ->
                    _studyInfo.value = data
                    LogUtils.i("获取学习信息 BizOK $data")
                    return@onBizOk
                }
            }.onFailure {
                LogUtils.e("获取学习信息 接口异常 ${it.message}")
            }
    }


    override suspend fun getStudyList(): Flow<PagingData<StudiedRsp.Data>> {
        val config =PagingConfig(
            pageSize = pageSize,
            prefetchDistance = 5,
            initialLoadSize = 10,
            maxSize = pageSize * 3
        )
        return Pager(config, null){
            StudiedItemPagingSource(service)
        }.flow

    }


    override suspend fun getBoughtCourse(): Flow<PagingData<BoughtRsp.Data>> {
        val config =
            PagingConfig(
                pageSize = pageSize,
                prefetchDistance = 5,
                initialLoadSize = 10,
                maxSize = pageSize * 3
            )
        return Pager(config = config, null) {
            BoughtItemPagingSource(service)
        }.flow
    }
}