package com.starter.file.app.file.models

import org.springframework.core.io.InputStreamResource

data class FileDownloadInfo(
    val inputStreamResource: InputStreamResource,
    val fileName: String,
    val fileSize: Long,
)
