package com.starter.core.clients.internal.file.api

import com.starter.core.clients.common.resolver.ModelAttributeArgumentResolver
import com.starter.core.models.file.FileResponse
import com.starter.core.models.file.FileUploadRequest
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.annotation.HttpExchange
import org.springframework.web.service.annotation.PostExchange
import org.springframework.web.service.invoker.HttpServiceProxyFactory


@HttpExchange("/api/v1/files/upload")
interface FileUploadApiClient : FileUploadApi {

    companion object {
        fun create(webClient: WebClient): FileUploadApiClient {
            val httpServiceProxyFactory = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(webClient))
                .customArgumentResolver(
                    ModelAttributeArgumentResolver.DEFAULT_INSTANCE
                )
                .build()
            return httpServiceProxyFactory.createClient(FileUploadApiClient::class.java)
        }
    }

    @PostExchange(contentType = MediaType.MULTIPART_FORM_DATA_VALUE)
    override fun uploadFile(@ModelAttribute fileUploadRequest: FileUploadRequest): FileResponse


}
