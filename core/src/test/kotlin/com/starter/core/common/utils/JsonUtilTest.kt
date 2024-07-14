package com.starter.core.common.utils

import io.kotest.core.spec.style.AnnotationSpec
import mu.KLogging

class JsonUtilTest : AnnotationSpec() {
    companion object : KLogging()

    @Test
    fun convertValue() {
        val test = TestClass(1, "test", listOf("1","2","3"))
        val map = JsonUtil.convertValue(test, Map::class.java) as Map<String, Any>
        logger.info { "map : $map" }

    }
}
