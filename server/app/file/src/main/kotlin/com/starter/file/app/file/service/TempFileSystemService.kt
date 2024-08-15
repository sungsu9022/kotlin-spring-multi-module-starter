package com.starter.file.app.file.service

import com.starter.core.tempfile.config.TempFileProperty
import com.starter.core.tempfile.service.FileSystemRotationService
import mu.KLogging
import org.springframework.stereotype.Service

@Service
class TempFileSystemService(
    private val fileProperty: TempFileProperty,
): FileSystemRotationService {
    companion object : KLogging() {
        private const val DEFAULT_DIR_DELETE_HOURS = 1L
        private const val DEFAULT_DIR_PREFIX = "tmp_"
        private val DEFAULT_FILE_CREATED_AT_REG_EXP: Regex = "$DEFAULT_DIR_PREFIX.+_(\\d{14}).*".toRegex()
    }

}
