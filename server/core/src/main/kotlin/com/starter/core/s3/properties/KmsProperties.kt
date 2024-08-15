package com.starter.core.s3.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("aws.kms")
data class KmsProperties(
    val kmsId: String
)
