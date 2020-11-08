package com.mvvm.common.ktx

import android.graphics.Rect
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import java.lang.Math.abs

/**
 * @creater：lbw
 * @time：20/9/17 11:10
 * @desc： View 扩展函数和扩展属性
 */
//region 扩展函数





//endregion



//region 扩展属性

/** 判断 View 是否在屏幕上 */
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


//region 扩展函数
/** View的扩展方法，用于检测输入坐标是否落在指定子控件内,并相应点击事件  */
inline fun View.onChildViewClick(
    vararg layoutId: String, // View的子控件Id（若输入多个则表示多个控件所组成的并集矩形区域）
    x: Float, // 触点横坐标
    y: Float,// 触点纵坐标
    clickAction: (View?) -> Unit // 子控件点击响应事件
){
    var clickedView: View ?= null
    //遍历所有子控件id
    layoutId.map {id ->
        //根据id查出子控件实例
        find<View>(id)?.let{ view ->
            //获取子空间相对于父控件的矩形区域
            view.getRelativeRectTo(this).also { rect ->
                // 如果矩形区域包含触点则表示子控件被点击（记录被点击的子控件）
                if (rect.contains(x.toInt(), y.toInt())) {
                    clickedView = view
                }
            }
        } ?: Rect()
    }.fold(Rect()) { init, rect -> init.apply { union(rect) } }// 将所有子控件矩形区域做并集
        .takeIf { it.contains(x.toInt(), y.toInt()) }// 如果并集中包含触摸点，则表示并集所对应的大矩形区域被点击
        ?.let { clickAction.invoke(clickedView) } //执行clickAction点击事件代码逻辑
}

/**
 * 获取此[View]相对于[otherView]的相对位置
 * get relative position of this [View] relative to [otherView]
 */
fun View.getRelativeRectTo(otherView: View): Rect {
    //getGlobalVisibleRect获取 View可见部分 相对于 屏幕的坐标
    val parentRect = Rect().also { otherView.getGlobalVisibleRect(it) }
    val childRect = Rect().also { getGlobalVisibleRect(it) }
    return childRect.relativeTo(parentRect)
}

/**
 * 根据[otherRect]得出[Rect]的相对rect，考虑到[otherRect]的左上角为零
 * get the relative rect of the [Rect] according to the [otherRect] ,considering the [otherRect]'s left and top is zero
 */
fun Rect.relativeTo(otherRect: Rect): Rect {
    val relativeLeft = left - otherRect.left
    val relativeTop = top - otherRect.top
    val relativeRight = relativeLeft + right - left
    val relativeBottom = relativeTop + bottom - top
    return Rect(relativeLeft, relativeTop, relativeRight, relativeBottom)
}

/** 将String id转换成 Int id */
val parent_id = "0"
fun String.toLayoutId(): Int {
    var id = hashCode()
    if (this == parent_id) id = 0
    return abs(id)
}
/**  绑定id 获取view */
fun <T : View> View.find(id: String): T? = findViewById(id.toLayoutId())
/**  绑定id 获取view */
fun <T : View> AppCompatActivity.find(id: String): T? = findViewById(id.toLayoutId())

//endregion