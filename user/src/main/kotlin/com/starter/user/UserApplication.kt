package com.starter.user

import com.starter.core.clients.internal.InternalClientConfig
import com.starter.core.common.config.CommonConfig
import com.starter.core.jasypt.config.JasyptConfig
import com.starter.core.rdb.config.DataSourceConfig
import com.starter.core.security.config.SecurityConfig
import com.starter.core.tempfile.config.TempFIleConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@ConfigurationPropertiesScan
@Import(value = [
    JasyptConfig::class,
    CommonConfig::class,
    SecurityConfig::class,
    DataSourceConfig::class,
    InternalClientConfig::class,
    TempFIleConfig::class,
])
class UserApplication

fun main(args: Array<String>) {
    runApplication<UserApplication>(*args)
}
