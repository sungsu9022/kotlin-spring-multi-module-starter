package com.starter.file.app.file.model

import org.springframework.web.multipart.MultipartFile

data class FileUploadRequest(
    val fileName: String,
    val file: MultipartFile
)
