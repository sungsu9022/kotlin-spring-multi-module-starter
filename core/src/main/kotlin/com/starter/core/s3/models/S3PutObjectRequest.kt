package com.starter.core.s3.models

import com.amazonaws.services.s3.model.ObjectMetadata
import com.starter.core.s3.mapper.S3ObjectMetadataMapper
import java.io.InputStream

data class S3PutObjectRequest(
    val fileInputStream: InputStream,
    val originalFileName: String,
    val s3Key: String,
    val fileSize: Long,
    val contentType: String,
    val userMetaDatas: Map<String, String> = emptyMap(),
    val public: Boolean = false
) {

    val s3ObjectMetadata: ObjectMetadata
        get() = S3ObjectMetadataMapper.INSTANCE.toS3ObjectMetadata(
            contentLength = fileSize,
            contentType = contentType,
            userMetadatas = userMetaDatas
        )
}
