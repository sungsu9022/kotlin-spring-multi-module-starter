package com.starter.file.app.file.facade

import com.starter.file.app.file.models.FileDownloadInfo
import com.starter.file.app.file.service.FileService
import com.starter.file.app.file.service.FileSystemService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class FileDownloadFacade(
    @Qualifier("localFileSystemService")
    private val fileSystemService: FileSystemService,
    private val fileService: FileService,

) {

    fun downloadFile(fileId: Long): FileDownloadInfo {
        val file = fileService.getOrThrow(fileId)
        val fileResource = fileSystemService.get(file.filePath)
        return FileDownloadInfo(
            fileName = file.fileName,
            fileSize = file.fileSize,
            inputStreamResource = fileResource
        )
    }
}
