package com.mvvm.mine

import android.graphics.Color
import androidx.databinding.ObservableField
import com.blankj.utilcode.util.ToastUtils
import com.mvvm.common.base.BaseActivity
import com.mvvm.mine.databinding.ActivityMineBinding
import com.mvvm.mine.databinding.FragmentMineBinding
import com.mvvm.mine.widge.ItemSettingsBean

/**
 * @desc：
 */
class MineActivity: BaseActivity<ActivityMineBinding>() {

    override fun getLayoutRes(): Int = R.layout.activity_mine


    override fun initView() {
        super.initView()
        mBinding.apply {
            val ib = ItemSettingsBean(iconRes = R.drawable.ic_shoping, title = "学习卡")
            val obBean = ObservableField(ib)
            bean = obBean

            ib.title = "你的学习卡"
            ib.titleColor = Color.RED

            ib.arrowColor = R.color.colorPrimary

            ib.iconRes = "https://www.easyicon.net/api/resizeApi.php?id=1283371&size=96"
//            isvCard.setIcon("https://www.easyicon.net/api/resizeApi.php?id=1283371&size=96")

            isvCard.onClickArrow {
                ToastUtils.showShort("点击了Arrow箭头")
            }
            isvCard.setOnClickListener {
                ToastUtils.showShort("点击整个Item")
            }
        }
    }

    override fun initConfig() {
        super.initConfig()

    }

}