package com.starter.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication


@ConfigurationPropertiesScan(basePackages = ["com.starter"])
@SpringBootApplication(
	scanBasePackages = ["com.starter.api", "com.starter.core"],
	exclude = [RedisAutoConfiguration::class, DataSourceAutoConfiguration::class]
)
class ApiApplication

fun main(args: Array<String>) {
	runApplication<ApiApplication>(*args)
}