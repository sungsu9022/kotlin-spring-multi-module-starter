package com.starter.file.app.file.models

import com.starter.core.rdb.domain.file.models.FileFormat
import com.starter.core.rdb.domain.file.models.FileStorageType
import com.starter.core.rdb.domain.file.models.FileType

data class FileResponse(
    val id: Long,
    val fileUuid: String,
    val filePath: String,
    val fileStorageType: FileStorageType,
    val fileName: String,
    val fileSize: Long,
    val fileFormat: FileFormat,
    val fileType: FileType,
    val deleted: Boolean,
)
