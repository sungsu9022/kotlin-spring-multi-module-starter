package com.starter.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication


@ConfigurationPropertiesScan
@SpringBootApplication(
    scanBasePackages = ["com.starter.api", "com.starter.core"],
)
class ApiApplication

fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
}
