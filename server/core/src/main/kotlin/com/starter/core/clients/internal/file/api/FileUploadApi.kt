package com.starter.core.clients.internal.file.api

import com.starter.core.models.file.FileResponse
import com.starter.core.models.file.FileUploadRequest

interface FileUploadApi {
    fun uploadFile(fileUploadRequest: FileUploadRequest): FileResponse
}
