package com.starter.core.clients.internal.file

import com.starter.core.clients.common.WebClientFactory
import com.starter.core.clients.internal.InternalClientsProperties
import com.starter.core.clients.internal.file.api.FileDownloadApiClient
import com.starter.core.clients.internal.file.api.FileUploadApiClient
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
@ConfigurationPropertiesScan("com.starter.core.clients.internal.file")
@ComponentScan("com.starter.core.clients.internal.file")
class FileClientConfig(
    private val internalClientsProperties: InternalClientsProperties
) {
    companion object {
        private const val FILE_WEB_CLIENT = "fileWebClient"
    }

    @Bean(FILE_WEB_CLIENT)
    fun fileWebClient(): WebClient {
        return WebClientFactory
            .createNettyClient(baseUrl = internalClientsProperties.file)
    }

    @Bean
    fun fileDownloadApiClient(@Qualifier(FILE_WEB_CLIENT) webClient: WebClient): FileDownloadApiClient {
        return FileDownloadApiClient.create(webClient)
    }

    @Bean
    fun fileUploadApiClient(@Qualifier(FILE_WEB_CLIENT) webClient: WebClient): FileUploadApiClient {
        return FileUploadApiClient.create(webClient)
    }
}
