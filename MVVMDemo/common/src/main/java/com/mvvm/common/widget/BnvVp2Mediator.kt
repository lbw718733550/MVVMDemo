package com.mvvm.common.widget

import android.view.MenuItem
import androidx.core.view.forEachIndexed
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * BottomNavigationView 和 ViewPager2 的中间桥梁类
 */
class BnvVp2Mediator(
    private val bnv: BottomNavigationView,
    private val vp2: ViewPager2,
    private val config: ((BottomNavigationView, ViewPager2) -> Unit) ?= null
) {

    private var map = mutableMapOf<MenuItem, Int>()

    init {
        //对每个item和所在位置做一个map映射
        bnv.menu.forEachIndexed { index, item ->
            map[item] = index
        }
    }

    /**  放attach中让语义更通顺 BnvVp2Mediator().attach() */
    fun attach(){
        //调用config设置
        config?.invoke(bnv, vp2)
        //viewpage页面更改回调
        vp2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            //页面选择
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                //切换bottomNavigationView对应id
                bnv.selectedItemId = bnv.menu.getItem(position).itemId
            }
        })

        //bnv更改的时候切换对应viewpage元素
        bnv.setOnNavigationItemSelectedListener { item ->
            vp2.currentItem = map[item] ?: error("bnv的item的id${item.itemId} 没有对应的viewpage2的元素")
            true
        }
    }

}