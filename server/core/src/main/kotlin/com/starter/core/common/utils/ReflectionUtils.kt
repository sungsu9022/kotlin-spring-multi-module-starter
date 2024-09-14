package com.starter.core.common.utils

import mu.KLogging
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

object ReflectionUtils : KLogging(){

    fun objectToMap(obj: Any?): Map<String, Any?> {
        if(obj == null) {
            return emptyMap()
        }

        val map = obj::class.memberProperties.associate { p ->
            p.isAccessible = true
            (p as KProperty1<Any, *>).let { prop ->
                prop.name to prop.get(obj)
            }
        }
        return map
    }



}
