package com.starter.core.common.response

import com.starter.core.common.exception.ErrorCode

data class ErrorResponse(
    val errorCode: ErrorCode,
    val message: String,
) {

    companion object {
        fun of(errorCode: ErrorCode, message: String) = ErrorResponse(errorCode, message)
    }
}
