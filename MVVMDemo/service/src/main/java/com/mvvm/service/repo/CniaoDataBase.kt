package com.mvvm.service.repo

import android.content.Context
import androidx.annotation.Keep
import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * 菜鸟App的公共业务基础数据类
 */
@Database(entities = [CniaoUserInfo::class], version = 1, exportSchema = false)
abstract class CniaoDataBase : RoomDatabase() {

    abstract val userDao: UserDao

    companion object{

        private const val CNIAO_DB_NAME = "cniao_db"

        @Volatile
        private var instance:CniaoDataBase? = null


        fun get(context: Context): CniaoDataBase{
            return instance ?:Room.databaseBuilder(
                context,
                CniaoDataBase::class.java,
                CNIAO_DB_NAME
            ).build().also { instance = it }
        }


    }



}

@Entity(tableName = "tb_cniao_user")
data class CniaoUserInfo(
    @PrimaryKey
    val id: Int,//主键
    val course_permission: Boolean,
    val token: String?,
    @Embedded
    val user: User?
) {
    @Keep
    data class User(
        @ColumnInfo(name = "cniao_user_id")
        val id: Int,//用户id
        val logo_url: String?,//用户头像
        val reg_time: String?,//用户注册时间
        val username: String?//用户名
    )
}

/**  Dao 层 */
@Dao
interface UserDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: CniaoUserInfo)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(user: CniaoUserInfo)

    @Delete
    fun deleteUser(user: CniaoUserInfo)

    // = , in , like
    @Query("select * from tb_cniao_user where id=:id")
    fun queryLiveUser(id: Int = 0): LiveData<CniaoUserInfo>

    @Query("select * from tb_cniao_user where id=:id")
    fun queryUser(id: Int = 0): CniaoUserInfo?

}
