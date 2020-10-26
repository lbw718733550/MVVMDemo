package com.permission.permission

import com.mvvm.common.permission.annotation.PermissionGrant
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.TypeElement

/**
 *
 */
class RuntimePermissionAbstractProcessor() : AbstractProcessor() {


    override fun init(p0: ProcessingEnvironment?) {
        super.init(p0)
    }

    override fun process(p0: MutableSet<out TypeElement>?, p1: RoundEnvironment?): Boolean {

        return false
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        //这里只处理权限这3种
        val supportList = mutableSetOf(PermissionGrant::class.java.canonicalName)

        return super.getSupportedAnnotationTypes()
    }

}