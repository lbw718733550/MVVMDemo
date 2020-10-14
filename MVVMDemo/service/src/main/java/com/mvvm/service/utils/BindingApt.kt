package com.mvvm.service.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * 项目适配用的BindAdapter
 */

/**
 * imageView支持图片加载 绑定
 */
@BindingAdapter("app:srcCompat", requireAll = false)
fun imgSrc(iv: ImageView, src: Any?){
    Glide.with(iv)
        .load(src)
        .into(iv)
}

/**
 * 图片资源支持tint颜色修改，支持colorRes和colorInt形式
 */
@BindingAdapter("app:tint")
fun imgColor(iv: ImageView, color: Int){
    if(color == 0) return
    //这里判断是colorRes还是colorInt，因为如果不是资源文件的颜色iv.resources.getColor会报错，
    //报错就说明是colorInt类型，会在onFailure调用对应方法
    runCatching {
        iv.setColorFilter(iv.resources.getColor(color))
    }.onFailure {
        iv.setColorFilter(color)
    }
}

/**
 * textColor的binding形式，支持colorRes和colorInt形式
 */
@BindingAdapter("android:textColor")
fun tvColor(tv: TextView, color: Int) {
    if (color == 0) return
    runCatching {
        tv.setTextColor(tv.resources.getColor(color))
    }.onFailure {
        tv.setTextColor(color)
    }
}

