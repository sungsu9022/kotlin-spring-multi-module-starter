package com.starter.file.app.file.service

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Path

@Service
class FileUploadService {

    fun uploadBy(
        path: Path,
        fileName: String,
        file: MultipartFile
    ) {
        // TODO path.
        // 파일 업로드 처리
    }
}
