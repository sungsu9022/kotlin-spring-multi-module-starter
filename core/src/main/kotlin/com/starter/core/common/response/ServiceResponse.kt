package com.starter.core.common.response

import com.fasterxml.jackson.annotation.JsonIgnore

interface ServiceResponse

open class BaseResponse<T>(
	val success: Boolean,
	val status: Status,
	var results: T?
) : ServiceResponse

class SuccessResponse<T: Any?>(status: Status, result: T) : BaseResponse<T>(true, status, result) {
	companion object {
		private const val OK = "ok"
		private val DEFAULT = create(Status.DEFAULT, OK)

		fun getDefault() : ServiceResponse {
			return DEFAULT
		}

		fun <T> create(results : T): ServiceResponse {
			return SuccessResponse(Status(ResponseCode.OK, OK), results)
		}

		fun <T> create(results : Collection<T>): ServiceResponse {
			return SuccessResponse(Status(ResponseCode.OK, OK), results);
		}

		fun <T> create(status: Status, results : T): ServiceResponse {
			return SuccessResponse(status, results);
		}

		fun create(status: Status): ServiceResponse {
			return SuccessResponse(status, null)
		}
	}
}

class FailureResponse(val status: Status) : ServiceResponse {
	companion object {
		fun create(responseCode : ResponseCode, message: String): ServiceResponse {
			return FailureResponse(Status(responseCode, message))
		}

		fun create(responseCode : ResponseCode): ServiceResponse {
			return FailureResponse(Status(responseCode, ""))
		}
	}

	val success: Boolean = false
}

data class Status (
	@JsonIgnore
	val responseCode: ResponseCode,
	val message: String = ""
) {
	companion object {
		val DEFAULT = Status(ResponseCode.OK)
	}

	val code: Int
		get() = responseCode.code
}
