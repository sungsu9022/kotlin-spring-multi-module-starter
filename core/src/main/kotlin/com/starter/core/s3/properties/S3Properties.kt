package com.starter.core.s3.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("aws.s3.credentials")
data class S3Properties(
    val profileName: String,
    val region: String,
    val bucket: String,
)


