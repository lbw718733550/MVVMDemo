package com.mvvm.mvvmdemo

import android.view.MenuItem
import androidx.core.view.forEachIndexed
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.cniao5.mine.MineContainerFragment
import com.mvvm.mvvmdemo.databinding.ActivityMainBinding
import com.mvvm.common.base.BaseActivity
import com.mvvm.common.widget.BnvVp2Mediator
import com.mvvm.course.CourseFragment
import com.mvvm.home.HomeFragment
import com.mvvm.study.StudyFragment

class Main2Activity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutRes() = R.layout.activity_main

    companion object {
        const val INDEXT_HOME = 0   //索引位置 - 首页
        const val INDEXT_COURSE = 1 //索引位置 - 课程
        const val INDEXT_STUDY = 2  //索引位置 - 学习
        const val INDEXT_MINE = 3   //索引位置 - 我的
    }
    /**
     *  这种就是不复用fragment每次切换都重新实例化
     *  下面就不是直接HomeFragment() 而是变成一个函数，直接生成一个新的fragment
     */
    private val fragments = mapOf<Int, Refragment>(
        INDEXT_HOME to { HomeFragment() },
        INDEXT_COURSE to { CourseFragment() },
        INDEXT_STUDY to { StudyFragment() },
        INDEXT_MINE to { MineContainerFragment() }
    )

    override fun initConfig() {
        super.initConfig()

    }

    override fun initView() {
        super.initView()
        mBinding.apply {
            vp2Main.adapter = MainViewPager2Adapter(this@Main2Activity, fragments)
            BnvVp2Mediator(bnvMain, vp2Main){bnv, vp2 ->
                //不需要用户滑动
                vp2.isUserInputEnabled = false
            }.attach()
        }
    }

    override fun initData() {
        super.initData()

    }
}

/**
 * 首页viewpage2的适配器
 */
class MainViewPager2Adapter(fragmentActivity: FragmentActivity,
                           private val fragments: Map<Int, Refragment>) : FragmentStateAdapter(fragmentActivity){

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        //这里改成invoke 实例化这个函数的内部实现
        return fragments[position]?.invoke() ?: throw IndexOutOfBoundsException("ViewPage接收参数index越界了！")
    }

}

/**  类型别名定义 */
typealias Refragment = () -> Fragment

