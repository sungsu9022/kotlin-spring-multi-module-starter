package com.starter.core.s3.utils

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import mu.KLogging
import java.time.Clock
import java.time.LocalDateTime
import java.time.ZoneId

class S3KeyUtilsTest : AnnotationSpec() {


    companion object : KLogging() {
        private val TARGET_DATETIME = LocalDateTime.of(2024,7,6,0,0,0)
        private val FIXED_CLOCK = Clock.fixed(
            TARGET_DATETIME.atZone(ZoneId.of("Asia/Seoul")).toInstant(),
            ZoneId.systemDefault()
        )
    }

    @Test
    fun newKey() {
        val s3Key = S3KeyUtils.newKey("xlsx", "test_file", FIXED_CLOCK)
        logger.info { "s3Key : $s3Key" }

        s3Key shouldBe "2024/7/6/test_file.xlsx"
    }
}
