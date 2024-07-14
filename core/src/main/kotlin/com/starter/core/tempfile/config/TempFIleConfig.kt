package com.starter.core.tempfile.config

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan("com.starter.core.tempfile")
@ConfigurationPropertiesScan("com.starter.core.tempfile")
class TempFIleConfig
