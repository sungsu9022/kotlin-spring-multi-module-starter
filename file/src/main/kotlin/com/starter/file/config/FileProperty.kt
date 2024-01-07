package com.starter.file.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("file")
data class FileProperty(
    val uploadPath: String,
    val tempFilePath: String
)
