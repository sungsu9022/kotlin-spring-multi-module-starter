package com.starter.core.clients.internal.file.api

import com.starter.core.clients.common.resolver.ModelAttributeArgumentResolver
import org.springframework.core.io.Resource
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange
import org.springframework.web.service.invoker.HttpServiceProxyFactory
import reactor.core.publisher.Flux


@HttpExchange("/api/v1/files")
interface FileDownloadApiClient : FileDownloadApi {

    companion object {
        fun create(webClient: WebClient): FileDownloadApiClient {
            val httpServiceProxyFactory = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(webClient))
                .customArgumentResolver(
                    ModelAttributeArgumentResolver.INSTANCE
                )
                .build()
            return httpServiceProxyFactory.createClient(FileDownloadApiClient::class.java)
        }
    }

    @GetExchange("/download")
    override fun download(@RequestParam fileId: Long): ResponseEntity<Resource>

    @GetExchange("/download")
    fun downloadV2(@RequestParam fileId: Long): ResponseEntity<Flux<DataBuffer>>


}
