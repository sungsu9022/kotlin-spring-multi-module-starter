package com.starter.core.tempfile.config

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling

@Configuration
@ComponentScan("com.starter.core.tempfile")
@ConfigurationPropertiesScan("com.starter.core.tempfile")
@EnableScheduling
class TempFileConfig
