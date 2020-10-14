package com.mvvm.mine.widge

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import androidx.annotation.Keep
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.ObservableField
import com.mvvm.mine.R
import com.mvvm.mine.databinding.VItemSettingsBinding

/**
 * 自定义的设置item的组合控件
 */
class ItemSettingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {


    private var itemBean = ItemSettingsBean()
    private val obItemInfo = ObservableField<ItemSettingsBean>(itemBean)

    init {
        //管理布局
        VItemSettingsBinding.inflate(LayoutInflater.from(context), this, true).apply {
            info = obItemInfo
        }
        //region 读取配置属性
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.ItemSettingsView).apply {
            //icon设置
            itemBean.iconRes = getResourceId(R.styleable.ItemSettingsView_icon, R.drawable.ic_gift_card)
            itemBean.iconColor = getInt(R.styleable.ItemSettingsView_icon, R.drawable.ic_gift_card)
            //title设置
            itemBean.title = getString(R.styleable.ItemSettingsView_title) ?: "Title标题"
            itemBean.titleColor = getColor(R.styleable.ItemSettingsView_titleColor, resources.getColor(R.color.colorPrimaryText)) as Int
            //desc设置
            itemBean.desc = getString(R.styleable.ItemSettingsView_desc) ?: "标题内容描述"
            itemBean.descColor = getColor(R.styleable.ItemSettingsView_descColor, 0) as Int
            //arrow设置
            itemBean.arrowRes = getResourceId(R.styleable.ItemSettingsView_arrow, R.drawable.ic_right)
            itemBean.arrowColor = getColor(R.styleable.ItemSettingsView_arrowColor, resources.getColor(R.color.colorSecondaryText)) as Int
        }
        // 回收 recycle
        attributes.recycle()
        //endregion
    }


    //region 设置资源

    /**
     * 设置整个item的对象info
     */
    fun setInfo(info: ItemSettingsBean) {
        itemBean = info
        obItemInfo.set(info)
    }

    /**
     * 设置title
     */
    fun setTitle(title: String) {
        itemBean.title = title
    }

    /**
     * 设置内容描述
     */
    fun setDesc(desc: String) {
        itemBean.desc = desc
    }

    /**
     * 设置icon图标
     */
    fun setIcon(iconRes: Any) {
        itemBean.iconRes = iconRes
    }

    /**
     * 设置icon图标
     */
    fun setArrow(arrowRes: Any) {
        itemBean.arrowRes = arrowRes
    }


    //endregion

    //region 点击事件

    /**
     * 单独点击图标
     */
    fun onClickIcon(listener: OnClickListener) {
        itemBean.iconListener = listener
    }

    /**
     * 单独点击title
     */
    fun onClickTitle(listener: OnClickListener) {
        itemBean.titleListener = listener
    }

    /**
     * 单独点击desc
     */
    fun onClickDesc(listener: OnClickListener) {
        itemBean.descListener = listener
    }

    /**
     * 单独点击箭头
     */
    fun onClickArrow(listener: OnClickListener) {
        itemBean.arrowListener = listener
    }

    //endregion

    //region 设置颜色

    /**
     * 设置标题title颜色
     */
    fun setIconColor(colorRes: Int) {
        itemBean.iconColor = colorRes
    }

    /**
     * 设置标题title颜色
     */
    fun setTitleColor(colorRes: Int) {
        itemBean.titleColor = colorRes
    }

    /**
     * 设置标题title颜色
     */
    fun setDescColor(colorRes: Int) {
        itemBean.descColor = colorRes
    }

    /**
     * 设置标题title颜色
     */
    fun setArrowColor(colorRes: Int) {
        itemBean.arrowColor = colorRes
    }

    //endregion

    /**
     * 此视图是否具有附加的OnClickListener，设置了子View就收不到点击事件
     */
    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return hasOnClickListeners()
    }

}

/** 数据类 */
@Keep
data class ItemSettingsBean(
    var iconRes: Any = R.drawable.ic_gift_card,
    var title: String = "Title标题",
    var desc: String = "标题内容描述",
    var titleColor: Int = R.color.colorPrimaryText,
    var descColor: Int = R.color.colorSecondaryText,
    var iconColor: Int = 0,
    var arrowColor: Int = 0,
    var arrowRes: Any = R.drawable.ic_right
){

    //item的子View的点击listener
    var iconListener: View.OnClickListener? = null
    var titleListener: View.OnClickListener? = null
    var descListener: View.OnClickListener? = null
    var arrowListener: View.OnClickListener? = null

}