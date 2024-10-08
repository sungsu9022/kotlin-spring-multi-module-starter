package com.starter.file.app.file.facade

import com.starter.core.models.file.FileResponse
import com.starter.core.models.file.FileUploadRequest
import com.starter.file.app.file.mapper.FileMetadataMapper
import com.starter.file.app.file.mapper.FileModelMapper
import com.starter.file.app.file.service.FileService
import com.starter.file.app.file.service.FileSystemService
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class FileUploadFacade(
    @Qualifier("localFileSystemService")
    private val fileSystemService: FileSystemService,
    private val fileService: FileService,
    private val fileMetadataMapper: FileMetadataMapper,
    private val fileMapper: FileModelMapper,

    ) {

    @Transactional
    fun uploadFile(request: FileUploadRequest): FileResponse {
        val metadata = fileMetadataMapper.toMetadata(request)
        val uploadResult = fileSystemService.upload(metadata)

        val file = fileMapper.toEntity(
            metadata = metadata,
            uploadResult = uploadResult,
        )
        val saved = fileService.save(file)
        return fileMapper.toResponse(saved)
    }
}
