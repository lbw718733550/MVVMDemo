package com.mvvm.common.ktx

import android.content.res.Resources
import android.util.TypedValue

/**
 * @creater：lbw
 * @time：20/9/17 11:15
 * @desc： Int扩展属性 和扩展函数
 */

//region 扩展函数
/**
 * 将 px 值转换成 dp 值
 */
val Int.dp: Int
    get() {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            Resources.getSystem().displayMetrics
        ).toInt()
    }


//endregion