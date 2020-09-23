package com.mvvm.common.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * @creater：lbw
 * @time：20/9/17 10:43
 * @desc： fragment基类
 */
abstract class BaseFragment : Fragment {

    constructor(): super()

    constructor(@LayoutRes layout: Int): super(layout)


    /**
     * 扩展LiveData的observer函数
     */
    protected inline fun <T : Any> LiveData<T>.observerKt(crossinline block: (T?) -> Unit) {
        this.observe(viewLifecycleOwner, Observer{
            block.invoke(it)
        })
    }

}