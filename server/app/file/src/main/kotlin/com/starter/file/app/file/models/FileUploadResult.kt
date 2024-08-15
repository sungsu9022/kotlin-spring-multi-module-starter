package com.starter.file.app.file.models

import com.starter.core.rdb.domain.file.models.FileStorageType

data class FileUploadResult(
    val storageType: FileStorageType,
    val storageKey: String,
)
