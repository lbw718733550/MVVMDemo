package com.mvvm.course

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.mvvm.common.base.BaseFragment
import com.mvvm.course.databinding.FragmentCourseBinding

/**
 * @creater：lbw
 * @time：20/9/27 14:42
 * @desc：课程中心fragment
 */
class CourseFragment : BaseFragment(R.layout.fragment_course){


    override fun getLayoutRes(): Int = R.layout.fragment_course

    override fun bindView(view: View, savedInstanceState: Bundle?): ViewDataBinding {
        return FragmentCourseBinding.bind(view)
    }


}
