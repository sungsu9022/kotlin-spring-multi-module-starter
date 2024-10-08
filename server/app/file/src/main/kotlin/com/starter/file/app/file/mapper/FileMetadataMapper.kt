package com.starter.file.app.file.mapper

import com.starter.core.models.file.FileFormat
import com.starter.core.models.file.FileUploadRequest
import com.starter.file.app.file.models.MultipartFileMetadata
import org.apache.commons.io.FilenameUtils
import org.mapstruct.Mapper

@Mapper
interface FileMetadataMapper {

    fun toMetadata(request: FileUploadRequest): MultipartFileMetadata {
        val fileName = request.fileName
        val fileFormat = FileFormat.of(fileName)

        return MultipartFileMetadata(
            originalFileName = fileName,
            fileExtension = FilenameUtils.getExtension(fileName),
            fileSize = request.file.size,
            fileFormat = FileFormat.of(fileName),
            contentType = fileFormat.getContentType(),
            fileType = request.fileType,
            file = request.file,
        )
    }

}
