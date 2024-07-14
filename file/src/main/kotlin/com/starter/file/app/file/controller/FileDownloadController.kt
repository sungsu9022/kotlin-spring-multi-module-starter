package com.starter.file.app.file.controller

import com.starter.core.common.utils.HttpHeaderUtils
import com.starter.file.app.file.facade.FileDownloadFacade
import org.springframework.core.io.Resource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/files/download")
class FileDownloadController(
    private val facade: FileDownloadFacade,
) {

    @GetMapping
    fun download(fileId: Long): ResponseEntity<Resource> {
        val fileDownloadInfo = facade.downloadFile(fileId)

        return ResponseEntity.ok()
            .headers(HttpHeaderUtils.makeFileDownloadHeaders(
                fileName = fileDownloadInfo.fileName,
                fileSize = fileDownloadInfo.fileSize
            ))
            .body(fileDownloadInfo.inputStreamResource)
    }
}
