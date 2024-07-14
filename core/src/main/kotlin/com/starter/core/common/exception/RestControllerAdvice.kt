package com.starter.core.common.exception

import com.starter.core.common.response.ErrorResponse
import mu.KLogging
import org.springframework.boot.logging.LogLevel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class RestControllerAdvice {
    companion object : KLogging() {
        private const val DEFAULT_ERROR_MESSAGE = "unknown error"
    }

    @ExceptionHandler(StarterException::class)
    fun handleServiceException(ex: StarterException): ResponseEntity<ErrorResponse> {
        loggingException(ex)

        val message = if(ex.message.isNullOrBlank()) {
            DEFAULT_ERROR_MESSAGE
        } else {
            ex.message
        }

        val errorResponse = ErrorResponse.of(
            errorCode = ex.errorCode,
            message = message,
        )

        return ResponseEntity(errorResponse, ex.errorCode.status)

    }

    @ExceptionHandler(Throwable::class)
    fun handleThrowable(ex: Throwable): ResponseEntity<ErrorResponse> {
        logger.error(ex.message, ex)

        val errorResponse = ErrorResponse.of(
            errorCode = ErrorCode.UNKNOWN,
            message = ex.message ?: DEFAULT_ERROR_MESSAGE
        )

        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    private fun loggingException(ex: StarterException) {
        when(ex.errorCode.logLevel) {
            LogLevel.TRACE -> logger.trace(ex.message, ex)
            LogLevel.DEBUG -> logger.debug(ex.message, ex)
            LogLevel.INFO -> logger.info(ex.message, ex)
            LogLevel.WARN -> logger.warn(ex.message, ex)
            LogLevel.ERROR, LogLevel.FATAL -> logger.error(ex.message, ex)
            else -> null
        }
    }
}
