package com.starter.api.config.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "starter.security")
data class SecurityProperty(
	val swaggerUIPath : String
)