package com.mvvm.common.ktx

import kotlin.reflect.KProperty

/**
 * @creater：lbw
 * @time：20/9/17 16:47
 * @desc： any 扩展函数
 */
class AnyKtx {
    /**
     * 将 data 类转换成 map
     */
    fun Any.ofMap() : Map<String, Any?>? =
        //过滤掉除Data.class以外的其他类
        //：：class获取KClass
        // KClass.isData判断该类是不是data class
        this::class.takeIf { it.isData }
            //遍历类的所有成员，过滤掉成员方法，只考虑成员属性
            //KClass.members：可访问的所有函数和属性，包括在此类*及其所有超类中声明的那些函数和属性。不包括构造函数，返回集合
            //filterIsInstance：过滤出集合中指定的类型
            //KProperty：表示属性
            ?.members?.filterIsInstance<KProperty<Any>>()
            //将成员属性名和值存储再Pair中
            ?.map {member ->
                //若成员变量是data class，则递归调用ofMap(),将其转换成键值对，否则直接返回值
                val value = member.call(this)?.let {v ->
                    if (v::class.isData) v.ofMap()
                    else v
                }
                member.name to value
            }
            //将Pair转成map
            ?.toMap()

}