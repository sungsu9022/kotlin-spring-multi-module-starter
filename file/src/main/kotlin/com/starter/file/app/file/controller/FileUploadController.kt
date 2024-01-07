package com.starter.file.app.file.controller

import com.starter.core.common.response.SuccessResponse
import com.starter.file.app.file.facade.FileUploadFacadeService
import com.starter.file.app.file.model.FileUploadRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/files/upload")
class FileUploadController(
    private val fileUploadFacadeService: FileUploadFacadeService,
) {

    @PostMapping
    fun uploadFile(fileUploadRequest: FileUploadRequest): SuccessResponse<String> {
        fileUploadFacadeService.uploadFile(fileUploadRequest)
        return SuccessResponse.DEFAULT
    }
}
