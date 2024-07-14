package com.starter.core.s3.utils

import com.starter.core.common.utils.UUIDUtils
import java.time.Clock
import java.time.LocalDate

object S3KeyUtils {

    fun newKey(
        fileExtension: String,
        fileName: String? = null,
        clock: Clock = Clock.systemDefaultZone()
    ) : String {
        val today = LocalDate.now(clock)
        val name = fileName ?: UUIDUtils.generate()
        return "${today.year}/${today.monthValue}/${today.dayOfMonth}/${name}.${fileExtension}"
    }
}
