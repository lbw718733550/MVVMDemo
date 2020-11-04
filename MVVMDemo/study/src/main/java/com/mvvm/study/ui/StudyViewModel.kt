package com.mvvm.study.ui

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.cniao5.study.net.StudyInfoRsp
import com.cniao5.study.net.StudyService
import com.mvvm.common.base.BaseViewModel
import com.mvvm.service.repo.CniaoUserInfo
import com.mvvm.study.repo.IStudyResource

/**
 * 创建者：lbw
 * 时间：6:39 20/11/4
 * 描述：
 */
class StudyViewModel(private val repo: IStudyResource) : BaseViewModel() {


    //学习页面的数据
    val liveStudyInfo: LiveData<StudyInfoRsp> = repo.liveStudyInfo

    //用户基本信息，头像和名字
    val obUserInfo = ObservableField<CniaoUserInfo>()


    //    val adapter=StudiedAdapter()
    val adapter = StudyPageAdapter()

    fun getStudyData() = serverAwait {
        repo.getStudyInfo()
    }

    suspend fun studiedList() =
        repo.getStudyList().asLiveData(viewModelScope.coroutineContext).cachedIn(viewModelScope)

    suspend fun boughtList() = repo.getBoughtCourse().asLiveData().cachedIn(viewModelScope)

}