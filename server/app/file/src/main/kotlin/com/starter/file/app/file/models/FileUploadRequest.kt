package com.starter.file.app.file.models

import com.starter.core.models.file.FileType
import org.springframework.web.multipart.MultipartFile

sealed interface UploadRequest {
    val fileType: FileType
    val description: String?
    val uploadDirectoryType: UploadDirectoryType
}

sealed interface FileUploadRequestInterface : UploadRequest {
    val file: MultipartFile
}

data class FileUploadRequest(
    val fileName: String,
    override val file: MultipartFile,
    override val fileType: FileType,
    override val description: String?,
    override val uploadDirectoryType: UploadDirectoryType
) : FileUploadRequestInterface
