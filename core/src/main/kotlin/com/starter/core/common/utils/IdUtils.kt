package com.starter.core.common.utils

import java.util.*

object IdUtils {
	fun generate(no: Long) : String {
		val uuid = UUID.fromString(no.toString())
		return uuid.toString()
	}

	fun generate() : String {
		val uuid = UUID.randomUUID()
		return uuid.toString()
	}
}