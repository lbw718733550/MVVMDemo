package com.mvvm.study.repo.data

import androidx.paging.PagingSource
import com.cniao5.study.net.StudiedRsp
import com.cniao5.study.net.StudyService

/**
 * 创建者：lbw
 * 时间：7:24 20/11/4
 * 描述：
 */
class StudiedItemPagingSource(private val service: StudyService) : PagingSource<Int, StudiedRsp.Data>(){


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StudiedRsp.Data> {

        var result:LoadResult<Int, StudiedRsp.Data>  = LoadResult.Error(Exception("加载中...."))


        return result
    }


}