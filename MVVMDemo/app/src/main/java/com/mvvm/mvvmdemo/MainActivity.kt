package com.mvvm.mvvmdemo

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.mvvm.mvvmdemo.databinding.ActivityMainBinding
import com.mvvm.common.base.BaseActivity
import com.mvvm.course.CourseFragment
import com.mvvm.home.HomeFragment
import com.mvvm.mine.MineFragment
import com.mvvm.study.StudyFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutRes() = R.layout.activity_main

    companion object {
        const val INDEXT_HOME = 0   //索引位置 - 首页
        const val INDEXT_COURSE = 1 //索引位置 - 课程
        const val INDEXT_STUDY = 2  //索引位置 - 学习
        const val INDEXT_MINE = 3   //索引位置 - 我的
    }
    /**  用于复用fragment */
    private val fragments = mapOf<Int, Fragment>(
        INDEXT_HOME to HomeFragment(),
        INDEXT_COURSE to CourseFragment(),
        INDEXT_STUDY to StudyFragment(),
        INDEXT_MINE to MineFragment()
    )

    override fun initConfig() {
        super.initConfig()

    }

    override fun initView() {
        super.initView()
        mBinding.apply {
            vp2Main.adapter = MainViewPagerAdapter(this@MainActivity, fragments)
            //viewpage页面更改回调
            vp2Main.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
                //页面选择
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    //切换bottomNavigationView对于id
                    bnvMain.selectedItemId = when(position){
                        INDEXT_HOME -> R.id.homeFragment
                        INDEXT_COURSE -> R.id.courseFragment
                        INDEXT_STUDY -> R.id.studyFragment
                        INDEXT_MINE -> R.id.mineFragment
                        else -> error("viewpage2的fragment索引位置${position}越界")
                    }
                }
            })
            //bnv更改的时候切换对应viewpage元素
            bnvMain.setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.homeFragment -> vp2Main.currentItem = INDEXT_HOME
                    R.id.courseFragment -> vp2Main.currentItem = INDEXT_COURSE
                    R.id.studyFragment -> vp2Main.currentItem = INDEXT_STUDY
                    R.id.mineFragment -> vp2Main.currentItem = INDEXT_MINE
                    else -> error("bnv的item的id${item.itemId} 没有对应的viewpage2的元素")
                }
                true
            }
        }
    }

    override fun initData() {
        super.initData()

    }
}

/**
 * 首页viewpage2的适配器
 */
class MainViewPagerAdapter(fragmentActivity: FragmentActivity,
                           private val fragments: Map<Int, Fragment>) : FragmentStateAdapter(fragmentActivity){



    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position] ?: error("请确保fragments数据源和viewpage2的index匹配设置")
    }


}