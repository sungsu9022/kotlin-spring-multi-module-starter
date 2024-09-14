package com.starter.core.common.utils

import com.starter.core.models.user.UserCreateRequest
import io.kotest.core.spec.style.AnnotationSpec
import mu.KLogging

class ReflectionUtilsTest : AnnotationSpec() {
    companion object : KLogging()

    @Test
    fun objectToMap() {
        val request = UserCreateRequest(
            email = "test@test.com",
            userName = "tester"
        )

        val map = ReflectionUtils.objectToMap(request)
        logger.info { "map : $map" }
    }


}
