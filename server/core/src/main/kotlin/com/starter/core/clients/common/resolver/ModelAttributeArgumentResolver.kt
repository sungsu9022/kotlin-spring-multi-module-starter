package com.starter.core.clients.common.resolver

import com.starter.core.common.utils.JsonUtil
import mu.KLogging
import org.springframework.core.MethodParameter
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.service.invoker.HttpRequestValues
import org.springframework.web.service.invoker.HttpServiceArgumentResolver
import kotlin.reflect.KFunction1

class ModelAttributeArgumentResolver(
    val argumentToMapFunc: KFunction1<Any, Map<String, Any?>> = JsonUtil::toMap,
) : HttpServiceArgumentResolver {
    companion object : KLogging() {
        val INSTANCE = ModelAttributeArgumentResolver()
    }

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

        val argumentMap = argumentToMapFunc(argument)
        logger.debug { "argumentMap : $argumentMap" }
        argumentMap
            .filter { it.value != null }
            .forEach { addRequestValue(name = it.key, value = it.value!!, requestValues = requestValues) }

        return true
    }

    private fun addRequestValue(name: String, value: Any, requestValues: HttpRequestValues.Builder) {
        if(value is Collection<*>) {
            requestValues.addRequestParameter(name, value.joinToString())
        } else {
            requestValues.addRequestParameter(name, value.toString())
        }
    }
}
