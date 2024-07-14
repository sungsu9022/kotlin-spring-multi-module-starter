package com.starter.core.s3

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.DeleteObjectRequest
import com.amazonaws.services.s3.model.GetObjectRequest
import com.amazonaws.services.s3.model.PutObjectRequest
import com.amazonaws.services.s3.model.S3Object
import com.amazonaws.services.s3.model.SSEAwsKeyManagementParams
import com.starter.core.common.exception.ErrorCode
import com.starter.core.common.exception.StarterException
import com.starter.core.s3.models.S3PutObjectRequest
import com.starter.core.s3.properties.KmsProperties
import com.starter.core.s3.properties.S3Properties
import mu.KLogging
import org.apache.commons.io.IOUtils
import org.springframework.stereotype.Service
import java.io.InputStream
import java.net.URLEncoder

@Service
class S3Client(
    private val amazonS3: AmazonS3,
    private val kmsProperties: KmsProperties,
    private val s3Properties: S3Properties
) {
    companion object : KLogging()

    fun putObject(
        request: S3PutObjectRequest
    ): String {
        val kmsParams = SSEAwsKeyManagementParams(kmsProperties.kmsId)

        val pubObjectRequest = PutObjectRequest(
            s3Properties.bucket,
            request.s3Key,
            request.fileInputStream,
            request.s3ObjectMetadata
        ).withSSEAwsKeyManagementParams(kmsParams)

        if(request.public) {
            pubObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead)
        }

        amazonS3.putObject(pubObjectRequest)
        IOUtils.closeQuietly(request.fileInputStream)

        return request.s3Key
    }

    fun getObjectInputStream(s3Key: String): InputStream {
        val s3Object = getObject(s3Key)
        return s3Object.objectContent
    }

    fun getObject(s3Key: String): S3Object {
        isExistsS3Object(s3Key)
        return amazonS3.getObject(GetObjectRequest(s3Properties.bucket, s3Key))
    }

    fun deleteObject(s3Key: String) {
        try {
            val deleteObjectRequest = DeleteObjectRequest(s3Properties.bucket, s3Key)
            amazonS3.deleteObject(deleteObjectRequest)
        } catch (e: Exception) {
            logger.error("aws s3 delete object error [ s3Key : $s3Key ]")
        }
    }

    private fun isExistsS3Object(s3Key: String) {
        val encodeKey = URLEncoder.encode(s3Key, "UTF-8").replace("%2F","/")
        try {
            if(amazonS3.doesObjectExist(s3Properties.bucket, s3Key).not()) {
                throw StarterException(ErrorCode.NOT_FOUND, "존재하지 않는 파일입니다. [s3key : $s3Key ]")
            }
        } catch (e: Exception) {
            throw StarterException(ErrorCode.NOT_FOUND, "s3 통신 에러 [encodeKey : $encodeKey, s3Key : $s3Key ]")
        }
    }
}

