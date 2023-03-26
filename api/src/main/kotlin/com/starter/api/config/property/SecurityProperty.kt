package com.starter.api.config.property

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "starter.security")
data class SecurityProperty(
    val swaggerUIPath: String
)
