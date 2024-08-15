package com.starter.core.common.utils

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.module.SimpleModule
import com.starter.core.common.serializer.LocalDateTimeDeserializer
import com.starter.core.common.serializer.LocalDateTimeSerializer
import mu.KLogging
import java.time.LocalDateTime

object JsonUtil : KLogging(){
    /** 기본 [ObjectMapper]  */
    val DEFAULT_OBJECT_MAPPER: ObjectMapper

    init {
        val ob = ObjectMapper()
        ob.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        ob.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
        // local date time 관련 셋팅 추가
        ob.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        ob.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        val module = SimpleModule()
        module.addSerializer(LocalDateTime::class.java, LocalDateTimeSerializer())
        module.addDeserializer(LocalDateTime::class.java, LocalDateTimeDeserializer())
        ob.registerModule(module)
        DEFAULT_OBJECT_MAPPER = ob
    }

    inline fun <reified T> convertValue(obj: Any?, clazz: Class<T>?): T {
        return convertValue(DEFAULT_OBJECT_MAPPER, obj, clazz)
    }

    inline fun <reified T> convertValue(objectMapper: ObjectMapper, obj: Any?, clazz: Class<T>?): T {
        return objectMapper.convertValue(obj, clazz) as T
    }

    inline fun <reified T: Any> fromObject(obj: Any): T = fromObject(DEFAULT_OBJECT_MAPPER, obj)

    inline fun <reified T: Any> fromObject(mapper: ObjectMapper, obj: Any): T = mapper.convertValue(obj, T::class.java)

    fun toMap(obj : Any): Map<String, Any?> = fromObject(DEFAULT_OBJECT_MAPPER, obj) as Map<String, Any?>

    fun toMap(mapper: ObjectMapper, obj : Any): Map<String, Any?> = fromObject(mapper, obj) as Map<String, Any?>

    fun toJsonNode(json : String): JsonNode {
        return toJsonNode(DEFAULT_OBJECT_MAPPER, json)
    }

    fun toJsonNode(mapper: ObjectMapper, json : String): JsonNode = mapper.readTree(json)

    fun toJson(obj: Any): String? {
        return toJson(DEFAULT_OBJECT_MAPPER, obj)
    }

    fun toJson(mapper: ObjectMapper, obj: Any): String? {
        return try {
            mapper.writeValueAsString(obj)
        } catch (e: Exception) {
            logger.error("Unable to serizalize to json : obj : $obj", e)
            return null
        }
    }

}
