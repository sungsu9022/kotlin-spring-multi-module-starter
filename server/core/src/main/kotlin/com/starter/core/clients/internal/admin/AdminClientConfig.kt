package com.starter.core.clients.internal.admin

import com.starter.core.clients.common.WebClientFactory
import com.starter.core.clients.internal.InternalClientsProperties
import com.starter.core.clients.internal.admin.api.UserApiClient
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
@ConfigurationPropertiesScan("com.starter.core.clients.internal.admin")
@ComponentScan("com.starter.core.clients.internal.admin")
class AdminClientConfig(
    private val internalClientsProperties: InternalClientsProperties
) {
    companion object {
        private const val ADMIN_WEB_CLIENT = "adminWebClient"
    }

    @Bean(ADMIN_WEB_CLIENT)
    fun adminWebClient(): WebClient {
        return WebClientFactory
            .createNettyClient(baseUrl = internalClientsProperties.admin)
    }

    @Bean
    fun userApiClient(@Qualifier(ADMIN_WEB_CLIENT) webClient: WebClient): UserApiClient {
        return UserApiClient.create(webClient)
    }
}
