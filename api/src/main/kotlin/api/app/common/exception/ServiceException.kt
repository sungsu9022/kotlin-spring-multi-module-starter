package com.starter.api.app.common.exception

class ServiceException(val errorCode: ErrorCode, override val message: String) : RuntimeException(message)