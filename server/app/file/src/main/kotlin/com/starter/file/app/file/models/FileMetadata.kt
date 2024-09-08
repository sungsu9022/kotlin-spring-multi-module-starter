package com.starter.file.app.file.models

import com.starter.core.models.file.FileFormat
import com.starter.core.models.file.FileType
import org.springframework.web.multipart.MultipartFile
import java.io.InputStream

sealed interface FileMetadata {
    val originalFileName: String
    val fileExtension: String
    val fileSize: Long
    val fileFormat: FileFormat
    val contentType: String
    val fileType: FileType
}

sealed interface InputStreamFileMetadata : FileMetadata {
    val inputStream: InputStream
}

class MultipartFileMetadata(
    override val originalFileName: String,
    override val fileExtension: String,
    override val fileSize: Long,
    override val fileFormat: FileFormat,
    override val contentType: String,
    override val fileType: FileType,
    val file: MultipartFile,
) : InputStreamFileMetadata {
    override val inputStream: InputStream
        get() = file.inputStream
}
