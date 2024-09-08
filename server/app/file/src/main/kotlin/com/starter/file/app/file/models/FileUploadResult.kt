package com.starter.file.app.file.models

import com.starter.core.models.file.FileStorageType

data class FileUploadResult(
    val storageType: FileStorageType,
    val storageKey: String,
)
