package com.mvvm.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
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


    private var mBinding : ViewDataBinding ?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutRes(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = bindView(view, savedInstanceState)
        mBinding?.lifecycleOwner = viewLifecycleOwner
        initConfig()
        initData()
    }

    @LayoutRes
    abstract fun getLayoutRes() : Int

    abstract fun bindView(view: View, savedInstanceState: Bundle?) : ViewDataBinding



    open fun initConfig() {}

    open fun initData() {}


    override fun onDestroy() {
        super.onDestroy()
        mBinding?.unbind()
    }

    /**
     * 扩展LiveData的observer函数
     */
    protected inline fun <T : Any> LiveData<T>.observeKt(crossinline block: (T?) -> Unit) {
        this.observe(viewLifecycleOwner, Observer{
            block.invoke(it)
        })
    }

}