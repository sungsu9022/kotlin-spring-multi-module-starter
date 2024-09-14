package com.starter.core.clients.common

import com.starter.core.clients.common.resolver.ModelAttributeArgumentResolver
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

object HttpInterfaceProxyFactory {

    inline fun <reified T> create(
        webClient: WebClient,
        resolvers: List<ModelAttributeArgumentResolver> = listOf(ModelAttributeArgumentResolver.DEFAULT_INSTANCE),
    ): T {
        val builder = HttpServiceProxyFactory
            .builderFor(WebClientAdapter.create(webClient))

        resolvers.forEach { builder.customArgumentResolver(it) }

        val httpServiceProxyFactory = builder.build()
        return httpServiceProxyFactory.createClient(T::class.java)
    }

}
