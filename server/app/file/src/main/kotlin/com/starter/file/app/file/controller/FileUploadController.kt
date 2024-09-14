package com.starter.file.app.file.controller

import com.starter.core.clients.internal.file.api.FileUploadApi
import com.starter.core.models.file.FileResponse
import com.starter.core.models.file.FileUploadRequest
import com.starter.file.app.file.facade.FileUploadFacade
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/files/upload")
class FileUploadController(
    private val fileUploadFacade: FileUploadFacade,
) : FileUploadApi {

    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE] )
    @Operation(description = "파일 업로드 API")
    override fun uploadFile(@Parameter fileUploadRequest: FileUploadRequest): FileResponse {
        val fileResponse = fileUploadFacade.uploadFile(fileUploadRequest)
        return fileResponse
    }
}
