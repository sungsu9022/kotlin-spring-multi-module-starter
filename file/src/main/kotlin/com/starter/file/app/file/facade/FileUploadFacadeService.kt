package com.starter.file.app.file.facade

import com.starter.file.app.file.model.FileUploadRequest
import com.starter.file.app.file.service.FileUploadService
import com.starter.file.app.file.service.TempFileService
import org.springframework.stereotype.Service

@Service
class FileUploadFacadeService(
    private val tempFileService: TempFileService,
    private val fileUploadService: FileUploadService
) {
    fun uploadFile(fileUploadRequest: FileUploadRequest) {
        val createTempFilePath = tempFileService.createTempFilePath()
        fileUploadService.uploadBy(
            path = createTempFilePath,
            fileName = fileUploadRequest.fileName,
            file = fileUploadRequest.file
        )
        TODO("Not yet implemented")
    }
}
