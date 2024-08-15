package com.starter.core.clients.internal.file

import com.starter.core.clients.common.resolver.ModelAttributeArgumentResolver
import org.springframework.core.io.Resource
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.invoker.HttpServiceProxyFactory
import reactor.core.publisher.Flux


interface FileApiClient {

    companion object {
        fun create(webClient: WebClient): FileApiClient {
            val httpServiceProxyFactory = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(webClient))
                .customArgumentResolver(
                    ModelAttributeArgumentResolver.INSTANCE
                )
                .build()
            return httpServiceProxyFactory.createClient(FileApiClient::class.java)
        }
    }

    @GetExchange("/api/v1/files/download")
    fun download(@RequestParam fileId: Long): ResponseEntity<Resource>

    @GetExchange("/api/v1/files/download")
    fun downloadV2(@RequestParam fileId: Long): ResponseEntity<Flux<DataBuffer>>


}
