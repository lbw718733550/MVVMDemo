package com.mvvm.mine

import androidx.lifecycle.MutableLiveData
import com.mvvm.common.base.BaseViewModel
import com.mvvm.service.repo.CniaoUserInfo

/**
 * 创建者：lbw
 * 时间：7:00 20/10/20
 * 描述：
 */
class MineViewModel : BaseViewModel(){

    val liveUser = MutableLiveData<CniaoUserInfo>()

}