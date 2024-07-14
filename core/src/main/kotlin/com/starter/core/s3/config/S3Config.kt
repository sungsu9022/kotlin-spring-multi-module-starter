package com.starter.core.s3.config

import com.amazonaws.ClientConfiguration
import com.amazonaws.auth.WebIdentityTokenCredentialsProvider
import com.amazonaws.auth.profile.ProfileCredentialsProvider
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3Client
import com.starter.core.common.config.Profiles
import com.starter.core.s3.properties.S3Properties
import mu.KLogging
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile

@Configuration
@ComponentScan("com.starter.core.s3")
@ConfigurationPropertiesScan("com.starter.core.s3")
class S3Config(
    private val s3Properties: S3Properties,
) {

    companion object : KLogging() {
        private const val S3_CONNECTION_TIMEOUT = 60 * 1000
        private const val S3_MAX_CONNECTION = 300
    }

    @Bean
    @Primary
    @Profile(Profiles.NOT_LOCAL)
    fun amazonS3(): AmazonS3 {
        val awsCredentialProvider = WebIdentityTokenCredentialsProvider
            .builder()
            .roleArn(System.getenv("AWS_ROLE_ARN"))
            .webIdentityTokenFile(System.getenv("AWS_WEB_IDENTITY_TOKEN_FILE"))
            .build()

        val clientConfiguration = ClientConfiguration()
            .withConnectionTimeout(S3_CONNECTION_TIMEOUT)
            .withMaxConnections(S3_MAX_CONNECTION)

        return AmazonS3Client.builder()
            .withCredentials(awsCredentialProvider)
            .withClientConfiguration(clientConfiguration)
            .build()
    }

    @Profile(Profiles.LOCAL)
    @Bean
    fun localAmazonS3() : AmazonS3 {
        return AmazonS3Client.builder()
            .withCredentials(ProfileCredentialsProvider(s3Properties.profileName))
            .withRegion(s3Properties.region)
            .build()
    }
}
