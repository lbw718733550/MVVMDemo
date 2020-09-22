package com.mvvm.common.model

import androidx.lifecycle.LiveData

/**
 * @creater：lbw
 * @time：20/9/17 16:15
 * @desc：创建一个空的liveData的对象类
 */
class AbsentLifeData<T : Any?> private constructor() : LiveData<T>(){

    init {
        postValue(null)
    }

    companion object {
        fun <T : Any?>create(): LiveData<T>{
            return AbsentLifeData<T>()
        }
    }

}