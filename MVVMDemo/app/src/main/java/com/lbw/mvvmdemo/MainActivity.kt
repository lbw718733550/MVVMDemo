package com.lbw.mvvmdemo

import android.os.Bundle
import android.os.PersistableBundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.lbw.mvvmdemo.databinding.ActivityMainBinding
import com.mvvm.common.base.BaseActivity
import com.mvvm.common.ktx.bindView

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutRes() = R.layout.activity_main


    override fun initConfig() {
        super.initConfig()

    }

    override fun initView() {
        super.initView()
        val navController = findNavController(R.id.fcv_main)
        mBinding.bnvMain.setupWithNavController(navController)
    }

    override fun initData() {
        super.initData()

    }
}