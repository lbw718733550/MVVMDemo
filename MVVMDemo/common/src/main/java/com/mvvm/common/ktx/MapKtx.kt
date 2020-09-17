package com.mvvm.common.ktx

/**
 * @creater：lbw
 * @time：20/9/17 17:11
 * @desc： Map 扩展函数
 */


//region 扩展函数

/**
 * 打印包含任意数据类型的Map，并将内容组织成更具可读性的字符串
 * 调用案例：Map.print { it.toString() }.let { Log.v("test","$it") }
 */
inline fun <K, V> Map<K, V>.print(map : (V?) -> String) =
    StringBuilder("\n{").also { sb ->
        //'遍历集合元素，通过 map 表达式将元素转换成感兴趣的字串，并独占一行'
        this.iterator().forEach { e -> sb.append("\n\t[${e.key}] = ${map(e.value)},") }
        sb.append("\n}")
    }.toString()




//endregion