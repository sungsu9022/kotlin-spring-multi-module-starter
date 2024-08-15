package com.starter.core.jasypt.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("jasypt.encryptor")
data class JasyptProperties(
    val password: String
)
