package com.starter.file

import com.starter.core.common.config.CommonConfig
import com.starter.core.jasypt.config.JasyptConfig
import com.starter.core.s3.config.S3Config
import com.starter.core.security.config.SecurityConfig
import com.starter.core.tempfile.config.TempFileConfig
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
    S3Config::class,
    TempFileConfig::class,
])
class FileApplication

fun main(args: Array<String>) {
    runApplication<FileApplication>(*args)
}
