package com.starter.core.jasypt.config

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties
import org.jasypt.encryption.StringEncryptor
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@EnableEncryptableProperties
@ConfigurationPropertiesScan("com.starter.core.jasypt")
@ComponentScan("com.starter.core.jasypt")
class JasyptConfig(
    private val jasyptProperties: JasyptProperties,
) {
    companion object {
        const val ENCRYPTOR_NAME = "jasyptEncryptor"

        internal fun create(password: String): PooledPBEStringEncryptor {
            val encryptor = PooledPBEStringEncryptor()
            val config = SimpleStringPBEConfig()
            config.password = password
            config.algorithm = "PBEWithMD5AndDES"
            config.setKeyObtentionIterations("1000")
            config.setPoolSize("1")
            config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator")
            config.stringOutputType = "base64"
            encryptor.setConfig(config)
            return encryptor
        }
    }

    @Bean(ENCRYPTOR_NAME)
    fun jasyptEncryptor(): StringEncryptor {
        return create(jasyptProperties.password)
    }
}
