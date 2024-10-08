package com.starter.file.app.file.service

import com.starter.core.common.exception.ErrorCode
import com.starter.core.common.exception.StarterException
import com.starter.file.repository.file.FileEntity
import com.starter.file.repository.file.FileRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class FileService(
    private val repository: FileRepository,
) {

    fun getOrThrow(fileId: Long): FileEntity {
        return repository.findByIdOrNull(fileId)
            ?: throw StarterException(ErrorCode.NOT_FOUND, "[FILE] File is not found. ( fileId : $fileId )")
    }

    @Transactional
    fun save(file: FileEntity): FileEntity {
        return repository.save(file)
    }
}
