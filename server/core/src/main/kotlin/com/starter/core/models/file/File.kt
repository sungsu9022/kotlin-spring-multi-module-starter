package com.starter.core.models.file

interface File {
    val id: Long
    val fileUuid: String
    val filePath: String
    val fileStorageType: FileStorageType
    val fileName: String
    val fileSize: Long
    val fileFormat: FileFormat
    val fileType: FileType
    val deleted: Boolean
}

