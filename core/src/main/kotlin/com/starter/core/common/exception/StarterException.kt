package com.starter.core.common.exception

class StarterException(
    val errorCode: ErrorCode,
    override val message: String,
    throwable: Throwable? = null,
) : RuntimeException(message, throwable) {
    constructor(errorCode: ErrorCode) : this(errorCode, "", null)
}
