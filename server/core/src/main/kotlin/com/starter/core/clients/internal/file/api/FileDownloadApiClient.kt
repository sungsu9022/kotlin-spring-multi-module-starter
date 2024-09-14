package com.starter.core.clients.internal.file.api

import org.springframework.core.io.Resource
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange
import reactor.core.publisher.Flux


@HttpExchange("/api/v1/files")
interface FileDownloadApiClient : FileDownloadApi {

    @GetExchange("/download")
    override fun download(@RequestParam fileId: Long): ResponseEntity<Resource>

    @GetExchange("/download")
    fun downloadV2(@RequestParam fileId: Long): ResponseEntity<Flux<DataBuffer>>


}
