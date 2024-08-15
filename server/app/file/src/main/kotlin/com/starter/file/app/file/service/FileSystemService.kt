package com.starter.file.app.file.service

import com.starter.file.app.file.models.FileUploadResult
import com.starter.file.app.file.models.InputStreamFileMetadata
import org.springframework.core.io.InputStreamResource

interface FileSystemService {
    fun upload(metadata: InputStreamFileMetadata): FileUploadResult

    fun get(key: String): InputStreamResource
}
