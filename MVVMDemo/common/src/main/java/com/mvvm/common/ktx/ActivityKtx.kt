package com.mvvm.common.ktx

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding

/**
 * 创建者：lbw
 * 时间：21:26 20/9/16
 * 描述：activity 相关的ktx,扩展函数或扩展属性
 */

//region 扩展函数

/**
 * 描述：activity中使用DataBinding时setContentView的简化
 * [layout] 布局id
 * @return 返回binding的对象实例
 */
fun <T : ViewDataBinding> Activity.bindView(@LayoutRes layout: Int) : T = DataBindingUtil.setContentView(this, layout)

/**
 * 描述：activity中使用DataBinding时setContentView的简化
 * [view] View
 * @return 返回binding的对象实例 T类型 可null的
 */
fun <T : ViewDataBinding> Activity.bindView(view: View) : T? = DataBindingUtil.bind<T>(view)

/**
 * 描述：沉浸式状态栏，使得可以在状态栏里面显示部分需要的图片
 * 注意：需要在setContentView之前调用该函数才生效
 */
fun Activity.immersiveStatusBar(){
    window.apply {
        addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN)
        addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }
}

/**
 * 描述：键盘的隐藏
 * [view] 事件控件view
 */
fun Activity.dismissKeyBoard(view: View) {
    val imm: InputMethodManager? = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager?
    imm?.hideSoftInputFromWindow(view.windowToken, 0)
}



//endregion


//region 扩展属性
/**  扩展lifrCycleOwner属性，便于和Fragment之间使用lifrCycleOwner 一致性 */
val ComponentActivity.viewLifeCycleOwner : LifecycleOwner get() = this

/**  Activity 扩展字段， 便于和Fragment中使用liveData之类的时候，参数一致性 */
val Activity.context : Context get() = this



//endregion
