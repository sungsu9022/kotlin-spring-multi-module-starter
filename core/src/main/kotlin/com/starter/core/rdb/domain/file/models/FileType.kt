package com.starter.core.rdb.domain.file.models

private const val DEFAULT_FILE_EXPIRED_DAYS = 30L

enum class FileType(val expiredDays: Long = DEFAULT_FILE_EXPIRED_DAYS) {
    BASIC,
    ETC,
}
