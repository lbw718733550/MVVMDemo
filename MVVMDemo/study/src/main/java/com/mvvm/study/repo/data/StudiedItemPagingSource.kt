package com.mvvm.study.repo.data

import androidx.paging.PagingSource
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
import com.mvvm.study.repo.StudyRepo

/**
 * 创建者：lbw
 * 时间：7:24 20/11/4
 * 描述：
 */
class StudiedItemPagingSource(private val service: StudyService) : PagingSource<Int, StudiedRsp.Data>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StudiedRsp.Data> {

        var result: LoadResult<Int, StudiedRsp.Data> = LoadResult.Error(Exception("加载中...."))

        val pageNum = params.key ?: 1

        service.getStudyList(page = pageNum, size = params.loadSize)
            .serverData()
            .onSuccess {
                onBizError { code, message ->
                    LogUtils.w("获取学习过的课程列表 BizError $code,$message")
                    result = LoadResult.Error(Exception(message))
                }
                onBizOk<StudiedRsp> { code, data, message ->
                    LogUtils.i("获取学习过的课程列表 BizOK $data")
                    val totalPage = data?.total_page ?: 0
                    result = LoadResult.Page(
                        data = data?.datas ?: emptyList(),
                        prevKey = if (pageNum == 1) null else pageNum - 1,//初始化的时候要为null，避免第一页重复加载
                        nextKey = if (pageNum < totalPage) pageNum + 1 else null
                    )
                }

            }.onFailure {
                LogUtils.w("获取学习过的课程列表 BizError ${it.message}")
                result = LoadResult.Error(it)
            }
        return result
    }
}
    /**
     * 已购买课程的分页数据
     */
    class BoughtItemPagingSource(private val service: StudyService) :
        PagingSource<Int, BoughtRsp.Data>() {

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BoughtRsp.Data> {

            var result: LoadResult<Int, BoughtRsp.Data> =
                LoadResult.Error(Exception("加载中...."))

            val pageNum = params.key ?: 1
            service.getBoughtCourse(page = pageNum, size = params.loadSize)
                .serverData()
                .onSuccess {
                    //只要不是接口响应成功，
                    onBizError { code, message ->
                        LogUtils.w("获取购买的课程 BizError $code,$message")
                        result = LoadResult.Error(Exception(message))
                    }
                    onBizOk<BoughtRsp> { code, data, message ->
                        LogUtils.i("获取购买的课程 BizOK $data")
                        val totalPage = data?.total_page ?: 0
                        result = LoadResult.Page(
                            data = data?.datas ?: emptyList(),
                            prevKey = null,//初始化的时候要为null，避免第一页重复加载
                            nextKey = if (pageNum < totalPage) pageNum.plus(1) else null
                        )
                    }
                }.onFailure {
                    LogUtils.e("获取购买的课程 接口异常 ${it.message}")
                    result = LoadResult.Error(it)
                }
            return result
        }
}
