package com.permission.permission

import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements

/**
 * 方法信息
 */
data class MethodInfo(
    /** 授权成功的方法信息<requestCode, 方法名称>  */
    var grantMethodMap: MutableMap<Int, String> = mutableMapOf(),
    /** 授权被拒绝的方法信息<requestCode, 方法名称>  */
    var deniedMethodMap: MutableMap<Int, String> = mutableMapOf(),
    /**  授权需不需要弹窗的方法信息<requestCode, 方法名称> */
    var rationalMethodMap: MutableMap<Int, String> = mutableMapOf(),
    /**  元素工具 */
    val elementUtil: Elements,
    /**  类所在的元素节点 */
    val typeElement: TypeElement,
    /**  包名 */
    var packageName: String = "",
    /**  类名 */
    var className: String = "",
    /**  需要创建的文件的文件名 */
    var fileName: String = ""
){

    private val PROXY_NAME: String = "PermissionProxy"

    init {
        //获取包名
        val packageElement = elementUtil.getPackageOf(typeElement)
        packageName = packageElement.qualifiedName.toString()
        //获取类名
        className = getClassName(typeElement, packageName)
        //创建的文件名
        fileName = "${className}$$${PROXY_NAME}"
    }

    fun generateJavaCode() : String {
        val builder = StringBuilder()
        return builder.run {
            append("// Generated code. Do not modify!\n")
            append("package $packageName;\n\n")
            append("import com.permission.helper.*;\n")
            append("\n")

            append("public class ${fileName} implements ${PROXY_NAME}<${className}>")
            append("{\n")

            geneateMethods(this)
            append("\n")

            append("}\n")
            toString()
        }

    }
    /** 编写方法 */
    private fun geneateMethods(builder: StringBuilder) {
        generateGrantMethod(builder)
        generateDeniedMethod(builder)
        generateRationaleMethod(builder)
    }
    /** 编写 权限请求成功 的方法 */
    private fun generateGrantMethod(builder: StringBuilder) {
        builder.apply {
            append("@Override\n");
            append("public void grant(${className} source, String[] permission) {\n")
            append("switch(requestCode) {");
            grantMethodMap.forEach{ entry ->
                append("case ${entry.key}:")
                append("source.${grantMethodMap[entry.key]}(permission);\n")
                append("break;\n")
            }
            append("} }\n")
        }

    }
    /** 编写 权限请求失败 的方法 */
    private fun generateDeniedMethod(builder: StringBuilder) {
        builder.apply {
            append("@Override\n");
            append("public void denied(${className} source, String[] permission) {\n")
            append("switch(requestCode) {");
            deniedMethodMap.forEach{ entry ->
                append("case ${entry.key}:")
                append("source.${deniedMethodMap[entry.key]}(permission);\n")
                append("break;\n")
            }
            append("} }\n")
        }
    }
    /** 编写 权限请求需要弹窗 的方法 */
    private fun generateRationaleMethod(builder: StringBuilder) {
        builder.apply {
            append("@Override\n");
            append("public boolean rational(${className} source, String[] permission) {\n")
            append("switch(requestCode) {");
            rationalMethodMap.forEach{ entry ->
                append("case ${entry.key}:")
                append("source.${rationalMethodMap[entry.key]}(permission);\n")
                append("return true;\n")
            }
            append("}");
            append("return false;")
            append("  }\n")
        }
    }

}