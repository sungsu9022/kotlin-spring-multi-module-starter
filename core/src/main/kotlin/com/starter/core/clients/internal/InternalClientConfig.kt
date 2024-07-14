package com.starter.core.clients.internal

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationPropertiesScan("com.starter.core.clients.internal")
@ComponentScan("com.starter.core.clients.internal")
class InternalClientConfig
