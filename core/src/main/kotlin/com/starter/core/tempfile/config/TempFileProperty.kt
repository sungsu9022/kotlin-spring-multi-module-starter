package com.starter.core.tempfile.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("temp-file")
data class TempFileProperty(
    val path: String,
)
