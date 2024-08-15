package com.starter.core.common.exception

import org.springframework.boot.logging.LogLevel
import org.springframework.http.HttpStatus

enum class ErrorCode(val status: HttpStatus, val logLevel: LogLevel) {
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, logLevel = LogLevel.DEBUG),
    DUPLICATED(HttpStatus.CONFLICT, logLevel = LogLevel.DEBUG),
    NOT_FOUND(HttpStatus.NOT_FOUND, logLevel = LogLevel.DEBUG),
    UN_SUPPORT(HttpStatus.BAD_REQUEST, logLevel = LogLevel.ERROR),
    UNKNOWN(HttpStatus.INTERNAL_SERVER_ERROR, logLevel = LogLevel.ERROR),
}
