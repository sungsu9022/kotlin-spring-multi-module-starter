package com.starter.admin

import com.starter.core.common.config.CommonConfig
import com.starter.core.jasypt.config.JasyptConfig
import com.starter.core.rdb.config.DataSourceConfig
import com.starter.core.security.config.SecurityConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@ConfigurationPropertiesScan
@SpringBootApplication
@Import(
    CommonConfig::class,
    JasyptConfig::class,
    SecurityConfig::class,
    DataSourceConfig::class
)
class AdminApplication

fun main(args: Array<String>) {
    runApplication<AdminApplication>(*args)
}
