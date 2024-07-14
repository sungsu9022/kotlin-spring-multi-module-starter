package com.starter.core.clients.common.resolver

import com.starter.core.common.utils.JsonUtil
import mu.KLogging
import org.springframework.core.MethodParameter
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.service.invoker.HttpRequestValues
import org.springframework.web.service.invoker.HttpServiceArgumentResolver

class ModelAttributeArgumentResolver : HttpServiceArgumentResolver {
    companion object : KLogging()

    override fun resolve(
        argument: Any?,
        parameter: MethodParameter,
        requestValues: HttpRequestValues.Builder
    ): Boolean {
        parameter.getParameterAnnotation(ModelAttribute::class.java)
            ?: return false

        if(argument == null) {
            return false
        }

        val argumentMap = JsonUtil.convertValue(argument, Map::class.java) as Map<String, Any>
        argumentMap.entries.forEach {
            val key = it.key
            val values = it.value
            if(values is Collection<*>) {
                val paramList = values.map { it.toString() }
                requestValues.addRequestParameter(key, *paramList.toTypedArray())
            } else {
                requestValues.addRequestParameter(key, values.toString())
            }
        }

        return true
    }
}
