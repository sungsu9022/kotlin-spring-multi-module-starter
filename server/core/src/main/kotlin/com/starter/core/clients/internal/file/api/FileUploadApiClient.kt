package com.starter.core.clients.internal.file.api

import com.starter.core.models.file.FileResponse
import com.starter.core.models.file.FileUploadRequest
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.service.annotation.HttpExchange
import org.springframework.web.service.annotation.PostExchange


@HttpExchange("/api/v1/files/upload")
interface FileUploadApiClient : FileUploadApi {

    @PostExchange(contentType = MediaType.MULTIPART_FORM_DATA_VALUE)
    override fun uploadFile(@ModelAttribute fileUploadRequest: FileUploadRequest): FileResponse


}
