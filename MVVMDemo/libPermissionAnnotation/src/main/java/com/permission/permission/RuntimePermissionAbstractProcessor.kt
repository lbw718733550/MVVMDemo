package com.permission.permission

import com.google.auto.service.AutoService
import com.mvvm.common.permission.annotation.PermissionDenied
import com.mvvm.common.permission.annotation.PermissionGrant
import com.mvvm.common.permission.annotation.PermissionRational
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.ExecutableElement
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements
import javax.tools.Diagnostic

/**
 * 权限注解   编译时注解处理器
 */
@AutoService(Processor::class)
class RuntimePermissionAbstractProcessor() : AbstractProcessor() {
    /** 元素辅助工具  元素指类里面 属性方法等 */
    var elementUtils: Elements ?= null
    /** 日志输出 */
    var messager: Messager ?= null
    /** 收集类里面的方法<key：类名，value：打上注解的方法> */
    var method: MutableMap<String, MethodInfo> = mutableMapOf()

    override fun init(environment: ProcessingEnvironment?) {
        super.init(environment)
        //拿到元素的辅助工具
        elementUtils = environment?.elementUtils
        //拿到messager 用于日志输出
        messager = environment?.messager
    }

    override fun process(set: MutableSet<out TypeElement>?, roundEnvironment: RoundEnvironment?): Boolean {
        method.clear()

        messager?.printMessage(Diagnostic.Kind.NOTE, "process start....")
        //处理各个注解 处理不了就返回false由系统自己处理
        if (!handleAnnotationInfo(roundEnvironment, PermissionGrant::class.java)) {
            return false
        }
        if (!handleAnnotationInfo(roundEnvironment, PermissionDenied::class.java)) {
            return false
        }
        if (!handleAnnotationInfo(roundEnvironment, PermissionRational::class.java)) {
            return false
        }

        return false
    }

    /** 处理注解 */
    private fun handleAnnotationInfo(
        roundEnvironment: RoundEnvironment?,
        annotation: Class<out Annotation>
    ): Boolean{
        //获取元素集合
        var elements =  roundEnvironment?.getElementsAnnotatedWith(annotation)
        elements?.forEach { element ->
            //如果不是一个有效的方法 直接 返回 false
            if(!checkMethodValidator(element, annotation)) return false
            //有效方法

            val executableElement = element as ExecutableElement
        }
        return true
    }

    /** 用于指示注释处理器支持的注释类型的注释 */
    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        //这里只处理权限这3种
        val supportList: MutableSet<String> = mutableSetOf(
            PermissionGrant::class.java.canonicalName,
            PermissionDenied::class.java.canonicalName,
            PermissionRational::class.java.canonicalName
        )

        return supportList
    }
    /** 判断是不是有效方法 */
    private fun checkMethodValidator(
        element: Element?,
        annotation: Class<out Annotation>
    ): Boolean {
        //类型不是方法
        //如果是方法，但是不是public的，也处理不了，返回false
        return element?.kind != ElementKind.METHOD && !isPrivate(element!!) && !isAbstract(element!!)
    }

    /** 用于指示注释处理器支持的最新源版本的注释 */
    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }


}