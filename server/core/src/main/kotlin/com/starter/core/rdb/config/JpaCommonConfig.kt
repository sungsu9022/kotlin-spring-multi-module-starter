package com.starter.core.rdb.config

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@Configuration
@EnableEncryptableProperties
@ConfigurationPropertiesScan("com.starter.core.rdb")
@ComponentScan("com.starter.core.rdb")
@EnableJpaAuditing
class JpaCommonConfig
