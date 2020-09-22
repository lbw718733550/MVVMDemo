package com.mvvm.common.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.mvvm.common.ktx.viewLifeCycleOwner

/**
 * @creater：lbw
 * @time：20/9/17 10:41
 * @desc： activity 基类
 */
abstract class BaseActivity : AppCompatActivity() {

    /**
     * 扩展LiveData的observer函数
     */
    protected inline fun <T : Any> LiveData<T>.observerKt(crossinline block: (T) -> Unit) {
        this.observe(viewLifeCycleOwner, {
            block.invoke(it)
        })
    }
}