package com.starter.file.app.file.mapper

import com.starter.core.common.utils.UUIDUtils
import com.starter.core.rdb.domain.file.repository.File
import com.starter.file.app.file.models.FileMetadata
import com.starter.file.app.file.models.FileResponse
import com.starter.file.app.file.models.FileUploadResult
import org.mapstruct.Mapper

@Mapper
interface FileMapper {

    fun toEntity(
        metadata: FileMetadata,
        uploadResult: FileUploadResult,
    ) = File(
        fileUuid = UUIDUtils.generate(),
        filePath = uploadResult.storageKey,
        fileStorageType = uploadResult.storageType,
        fileName = metadata.originalFileName,
        fileSize = metadata.fileSize,
        fileFormat = metadata.fileFormat,
        fileType = metadata.fileType,
    )

    fun toResponse(file: File): FileResponse

}
