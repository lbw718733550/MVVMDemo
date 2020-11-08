package com.mvvm.common.ktx

import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * recycler 扩展函数 和属性
 */


//region 扩展属性

//endregion

//region 扩展函数
/**
 * recyclerView item点击事件
 * 在OnItemTouchListener中使用GestureDetector判断单击事件是哪个item
 */
fun RecyclerView.setOnItemClickListener(listener: (View, Int, Float, Float) -> Unit) {
    addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
        val gestureDetector = GestureDetector(context, object : GestureDetector.OnGestureListener {
            override fun onShowPress(e: MotionEvent?) {
            }

            override fun onSingleTapUp(e: MotionEvent?): Boolean {
                e?.let {
                    findChildViewUnder(it.x, it.y)?.let { child ->
                        listener(
                            child,
                            getChildAdapterPosition(child),
                            it.x - child.left,
                            it.y - child.top
                        )
                    }
                }
                return false
            }

            override fun onDown(e: MotionEvent?): Boolean {
                return false
            }

            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent?,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                return false
            }

            override fun onScroll(
                e1: MotionEvent?,
                e2: MotionEvent?,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                return false
            }

            override fun onLongPress(e: MotionEvent?) {
            }
        })

        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

        }

        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
            gestureDetector.onTouchEvent(e)
            return false
        }

        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
        }
    })
}

//endregion