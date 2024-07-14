package com.starter.core.s3.mapper

import com.amazonaws.services.s3.model.ObjectMetadata
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface S3ObjectMetadataMapper {
    companion object {
        val INSTANCE = Mappers.getMapper(S3ObjectMetadataMapper::class.java)
    }

    fun toS3ObjectMetadata(
        contentLength: Long,
        contentType: String,
        userMetadatas: Map<String, String> = emptyMap()
    ) : ObjectMetadata {
        val metadata = ObjectMetadata().also {
            it.contentType = contentType
            it.contentLength = contentLength
        }

        userMetadatas.forEach {
            metadata.addUserMetadata(it.key, it.value)
        }

        return metadata
    }
}
