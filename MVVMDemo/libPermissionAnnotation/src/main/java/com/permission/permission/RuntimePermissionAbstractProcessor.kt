package com.permission.permission

import com.google.auto.service.AutoService
import com.mvvm.common.permission.annotation.PermissionDenied
import com.mvvm.common.permission.annotation.PermissionGrant
import com.mvvm.common.permission.annotation.PermissionRational
import java.io.IOException
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.ExecutableElement
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements
import javax.tools.Diagnostic
import javax.tools.JavaFileObject

/**
 * 权限注解   编译时注解处理器
 */
@AutoService(Processor::class)
class RuntimePermissionAbstractProcessor() : AbstractProcessor() {
    /** 元素辅助工具  元素指类里面 属性方法等 */
    lateinit var elementUtils: Elements
    /** 日志输出 */
    lateinit var messager: Messager
    /** 收集类里面的方法<key：类名，value：打上注解的方法> */
    var methodMap: MutableMap<String, MethodInfo?> = mutableMapOf()
    /** 用来文件生成的工具 */
    lateinit var filer: Filer

    override fun init(environment: ProcessingEnvironment?) {
        super.init(environment)
        //拿到元素的辅助工具
        elementUtils = environment!!.elementUtils
        //拿到messager 用于日志输出
        messager = environment.messager
        filer = environment.filer
    }

    override fun process(set: MutableSet<out TypeElement>?, roundEnvironment: RoundEnvironment?): Boolean {
        methodMap.clear()

        messager.printMessage(Diagnostic.Kind.NOTE, "process start....")
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
        //自动生成文件
        methodMap.forEach{ entry ->
            entry.value?.also { info ->
                try {
                    val sourcrFile = filer.createSourceFile("${info.packageName}.${info.fileName}")
                    val writer = sourcrFile?.openWriter()
                    writer?.apply {
                        write(info.generateJavaCode())
                        flush()
                        close()
                    }
                }catch (e: IOException){
                    messager.printMessage(Diagnostic.Kind.NOTE, "write file failed ${e.message}")
                }
            }
        }
        messager.printMessage(Diagnostic.Kind.NOTE, "process end....")

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
            val methodElement = element as ExecutableElement
            //得到元素的类名
            val enclosingElement = methodElement.enclosingElement as TypeElement
            val className = enclosingElement.qualifiedName.toString()
            //创造类里面实体放到methodMap里面
            var methodInfo = methodMap[className]
            //methodInfo 为空的时候创建
            methodInfo  ?: MethodInfo(elementUtil = elementUtils, typeElement = enclosingElement)
            methodMap[className] = methodInfo
            //得到方法上的注解
            val annotationClaz = methodElement.getAnnotation(annotation)
            //得到方法的名称
            val methodName = methodElement.simpleName.toString()
            //得到方法的入参
            val parameters = methodElement.parameters
            //参数错误的信息
            //判断方法是否有参数
            if (parameters == null || parameters.size < 1) {
                throw IllegalArgumentException("The method ${methodName} marked by annotation ${annotationClaz::class.java.simpleName} must have a unique parameter [string[] permission],which contains permission request denied results.")
            }
            //如果annotation 是权限的这几个类型的，强转
            when(annotationClaz){
                is PermissionGrant -> {
                        //存入对应权限注解的方法信息
                        val requestCode = annotationClaz.value
                        methodInfo!!.grantMethodMap[requestCode] = methodName
                    }
                is PermissionDenied -> {
                        //存入对应权限注解的方法信息
                        val requestCode = (annotationClaz as PermissionGrant).value
                        methodInfo!!.deniedMethodMap[requestCode] = methodName
                    }
                is PermissionRational -> {
                        //存入对应权限注解的方法信息
                        val requestCode = (annotationClaz as PermissionGrant).value
                        methodInfo!!.rationalMethodMap[requestCode] = methodName
                    }
            }

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
        element: Element,
        annotation: Class<out Annotation>
    ): Boolean {
        //类型不是方法
        if (element.kind != ElementKind.METHOD) {
            messager.printMessage(Diagnostic.Kind.NOTE, "${annotation.simpleName} no method")
            return false
        }
        //如果是方法，但是不是public的，也处理不了，返回false
        if (isPrivate(element) && isAbstract(element)) {
            messager.printMessage(Diagnostic.Kind.NOTE, "${annotation.simpleName} isPrivate or isAbstract")
            return false
        }
        return  true
    }

    /** 用于指示注释处理器支持的最新源版本的注释 */
    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }


}