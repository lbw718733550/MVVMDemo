package com.permission.permission

import javax.lang.model.element.Element
import javax.lang.model.element.Modifier

/**
 * 判断 修饰符
 */


/** 判断是否是私有方法 */
fun isPrivate(element: Element): Boolean{
    return element.modifiers.contains(Modifier.PRIVATE)
}

/** 判断是否是抽象 未实现方法 */
fun isAbstract(element: Element): Boolean{
    return element.modifiers.contains(Modifier.ABSTRACT)
}

