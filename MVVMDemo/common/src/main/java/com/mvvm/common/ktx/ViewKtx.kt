package com.mvvm.common.ktx

import android.graphics.Rect
import android.view.View

/**
 * @creater：lbw
 * @time：20/9/17 11:10
 * @desc： View 扩展函数和扩展属性
 */
//region 扩展函数





//endregion



//region 扩展属性

/**
 * 判断 View 是否在屏幕上
 */
val View.inScreen: Boolean
    get() {
        // 获取屏幕宽度
        val screenWidth = context?.resources?.displayMetrics?.widthPixels ?: 0
        // 获取屏幕高度
        val screenHeight = context?.resources?.displayMetrics?.heightPixels ?: 0
        // 构建屏幕矩形
        val screenRect = Rect(0, 0, screenWidth, screenHeight)
        val array = IntArray(2)
        // 获取视图矩形
        getLocationOnScreen(array)
        val viewRect = Rect(array[0], array[1], array[0] + width, array[1] + height)
        // 判断屏幕和视图矩形是否有交集
        return screenRect.intersect(viewRect)
    }


//endregion