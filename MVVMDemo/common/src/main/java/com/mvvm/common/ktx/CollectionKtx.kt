package com.mvvm.common.ktx

/**
 * @creater：lbw
 * @time：20/9/17 14:01
 * @desc：集合扩展函数
 */

//region 扩展函数

/**
 * 打印包含任意数据类型的列表，并将列表内容组织成更具可读性的字符串
 * 扩展函数+泛型+高阶函数
 * 调用案例
 * list.print { "${it.name}_${it.age}" }.let { Log.v("test",it) }
 */
inline fun <T> Collection<T>.print(map: (T) -> String) =
    StringBuilder("\n[").also { sb ->
        //'遍历集合元素，通过 map 表达式将元素转换成感兴趣的字串，并独占一行'
        this.forEach { e -> sb.append("\n\t${map(e)},") }
        sb.append("\n]")
    }.toString()



//endregion
