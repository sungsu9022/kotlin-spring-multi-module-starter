package com.starter.core.common.utils

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import mu.KLogging
import java.time.LocalDateTime

class DateTimeFormatUtilsTest : AnnotationSpec() {

    companion object : KLogging()
    @Test
    fun formatString() {
        val datetime = LocalDateTime.of(2024, 1, 7, 10, 0, 5,7)
        val formatString = DateTimeFormatUtils.formatString(datetime)

        logger.info { "formatString : $formatString" }
        formatString shouldBe "20240107100005007"

    }
}
