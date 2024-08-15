package com.starter.core.common.utils

import mu.KLogging
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor

object DateTimeFormatUtils : KLogging() {
    val yyyyMMddhhmmssnnn_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmssnnn")
    val yyyyMMddHHmmss_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
    val yyyyMMdd_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd")

    fun formatString(
        temporal: TemporalAccessor,
        formatter: DateTimeFormatter = yyyyMMddhhmmssnnn_FORMATTER
    ): String {
        return formatter.format(temporal)
    }
}
