package com.starter.core.models.file

data class FileResponse(
    override val id: Long,
    override val fileUuid: String,
    override val filePath: String,
    override val fileStorageType: FileStorageType,
    override val fileName: String,
    override val fileSize: Long,
    override val fileFormat: FileFormat,
    override val fileType: FileType,
    override val deleted: Boolean,
) : File
