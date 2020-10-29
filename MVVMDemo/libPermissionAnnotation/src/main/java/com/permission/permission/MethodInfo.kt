package com.permission.permission

import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements

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
){
    /**  包名 */
    var packageName: String = ""
    /**  类名 */
    var className: String = ""
    /**  需要创建的文件的文件名 */
    var fileName: String = ""
    val PROXY_NAME: String = "PermissionProxy"

    init {
        //获取包名
        val packageElement = elementUtil.getPackageOf(typeElement)
        packageName = packageElement.qualifiedName.toString()
        //获取类名
        className = getClassName(typeElement, packageName)
        //创建的文件名
        fileName = "${className}$$${PROXY_NAME}"
    }

    fun generateJavaCode(){

    }

}