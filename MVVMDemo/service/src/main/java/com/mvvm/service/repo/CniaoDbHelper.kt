package com.mvvm.service.repo

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/*
 *
 * cniao数据库操作帮助类
 */
object CniaoDbHelper {

    /**
     * 获取room数据表中存储的userInfo
     * return liveData形式
     */
    fun getLiveUserInfo(context: Context) =
        CniaoDataBase.get(context).userDao.queryLiveUser()

    /**
     * 以普通数据对象的形式，获取userInfo
     */
    fun getUserInfo(context: Context) = CniaoDataBase.get(context).userDao.queryUser()

    /**
     * 删除数据表中的userInfo信息
     */
    fun deleteUserInfo(context: Context) {
        GlobalScope.launch(Dispatchers.IO) {
            getUserInfo(context)?.let { info ->
                CniaoDataBase.get(context).userDao.deleteUser(info)
            }
        }
    }

    /**
     * 新增用户数据到数据表
     */
    fun insertUserInfo(context: Context, user: CniaoUserInfo) {
        GlobalScope.launch(Dispatchers.IO) {
            CniaoDataBase.get(context).userDao.insertUser(user)
        }
    }

}