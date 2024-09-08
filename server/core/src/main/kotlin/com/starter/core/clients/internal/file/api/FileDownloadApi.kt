package com.starter.core.clients.internal.file.api

import org.springframework.core.io.Resource
import org.springframework.http.ResponseEntity

interface FileDownloadApi {

    fun download(fileId: Long): ResponseEntity<Resource>
}
