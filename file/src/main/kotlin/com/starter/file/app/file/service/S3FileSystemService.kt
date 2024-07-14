package com.starter.file.app.file.service

import com.starter.core.rdb.domain.file.models.FileStorageType
import com.starter.core.s3.S3Client
import com.starter.core.s3.models.S3PutObjectRequest
import com.starter.core.s3.utils.S3KeyUtils
import com.starter.file.app.file.models.FileUploadResult
import com.starter.file.app.file.models.InputStreamFileMetadata
import mu.KLogging
import org.springframework.core.io.InputStreamResource
import org.springframework.stereotype.Service

@Service
class S3FileSystemService(
    private val s3Client: S3Client,
) : FileSystemService {
    companion object : KLogging()


    override fun upload(meatadata: InputStreamFileMetadata): FileUploadResult {
        val s3key = S3KeyUtils.newKey(
            fileExtension = meatadata.fileExtension
        )

        val s3Key = s3Client.putObject(
            S3PutObjectRequest(
                fileInputStream = meatadata.inputStream,
                originalFileName = meatadata.originalFileName,
                s3Key = s3key,
                fileSize = meatadata.fileSize,
                contentType = meatadata.contentType,
            )
        )
        return FileUploadResult(
            storageType = FileStorageType.S3,
            storageKey = s3key
        )
    }

    override fun get(key: String): InputStreamResource {
        val s3ObjectInputStream = s3Client.getObjectInputStream(key)
        return InputStreamResource(s3ObjectInputStream)
    }
}
