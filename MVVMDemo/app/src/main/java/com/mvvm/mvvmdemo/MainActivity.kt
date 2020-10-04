package com.mvvm.mvvmdemo

import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.mvvm.mvvmdemo.databinding.ActivityMainBinding
import com.mvvm.common.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutRes() = R.layout.activity_main


    override fun initConfig() {
        super.initConfig()

    }

    override fun initView() {
        super.initView()
        //简单联动bottomnvi和fragment的联动
        val navController = findNavController(R.id.fcv_main)
        mBinding.bnvMain.setupWithNavController(navController)
    }

    override fun initData() {
        super.initData()

    }
}