package com.starter.core.clients.common.resolver

import com.starter.core.common.utils.JsonUtil
import com.starter.core.common.utils.ReflectionUtils
import mu.KLogging
import org.springframework.core.MethodParameter
import org.springframework.core.io.Resource
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.lang.Nullable
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.service.invoker.HttpRequestValues
import org.springframework.web.service.invoker.HttpServiceArgumentResolver
import kotlin.reflect.KFunction1

class ModelAttributeArgumentResolver(
    val argumentToMapFunc: KFunction1<Any, Map<String, Any?>> = ReflectionUtils::objectToMap,
) : HttpServiceArgumentResolver {
    companion object : KLogging() {
        val DEFAULT_INSTANCE = ModelAttributeArgumentResolver()
        val INSTANCE_WITH_JSON = ModelAttributeArgumentResolver(
            JsonUtil::toMap
        )
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
        val isPartRequest = isPartRequest(argumentMap)
        logger.debug { "argumentMap: $argumentMap,  isPartRequest: $isPartRequest" }
        argumentMap
            .filter { it.value != null }
            .forEach {
                if(isPartRequest) {
                    addRequestPartValues(name = it.key, value = it.value!!, requestValues = requestValues)
                } else {
                    addRequestParamValues(name = it.key, value = it.value!!, requestValues = requestValues)
                }

            }

        return true
    }

    @Nullable
    private fun isPartRequest(argumentMap: Map<String,Any?>): Boolean {
        return argumentMap.values.any { it is MultipartFile }
    }


    private fun addRequestParamValues(name: String, value: Any, requestValues: HttpRequestValues.Builder) {
        if(value is Collection<*>) {
            requestValues.addRequestParameter(name, value.joinToString())
        } else {
            requestValues.addRequestParameter(name, value.toString())
        }
    }

    private fun addRequestPartValues(name: String, value: Any, requestValues: HttpRequestValues.Builder) {
        if(value is Collection<*>) {
            requestValues.addRequestPart(name, value.joinToString())
        } else if (value is MultipartFile) {
            val value = toMultipartFileHttpEntity(name, value)
            requestValues.addRequestPart(name, value)
        } else {
            requestValues.addRequestPart(name, value.toString())
        }
    }

    private fun toMultipartFileHttpEntity(name: String, multipartFile: MultipartFile): HttpEntity<Resource> {
        val headers = HttpHeaders()
        if (multipartFile.originalFilename != null) {
            headers.setContentDispositionFormData(name, multipartFile.originalFilename)
        }

        if (multipartFile.contentType != null) {
            headers.add(HttpHeaders.CONTENT_TYPE, multipartFile.contentType)

        }

        return HttpEntity<Resource>(multipartFile.resource, headers)
    }
}
