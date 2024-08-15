package com.starter.admin.app.userfile

import com.starter.core.clients.internal.file.FileApiClient
import com.starter.core.common.exception.ErrorCode
import com.starter.core.common.exception.StarterException
import com.starter.core.common.utils.FileUtils
import com.starter.core.common.utils.FluxDataBufferUtils
import com.starter.core.tempfile.service.TempFileSystemRotationService
import mu.KLogging
import org.springframework.core.io.Resource
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux

@Component
class UserFileFacade(
    private val fileApiClient: FileApiClient,
    private val tempFileSystemRotationService: TempFileSystemRotationService,
) {
    companion object : KLogging()

    fun getFile(fileId: Long): ResponseEntity<Resource> {
        return fileApiClient.download(fileId)
    }

    fun getFileDataBuffer(fileId: Long): ResponseEntity<Flux<DataBuffer>> {
        return fileApiClient.downloadV2(fileId)
    }

    fun writeLocal(fileId: Long) {
        val tempFilePath = tempFileSystemRotationService.getPrivatePath()
        for(i in 1..3) {
            val dataBuffer = fileApiClient.downloadV2(fileId)
            val body = dataBuffer.body
                ?: throw StarterException(ErrorCode.UNKNOWN, "fileBody is null ( fileId : $fileId")

            val outputFilePath = tempFilePath.resolve("file${i}.zip")
            val fileInputStream = FluxDataBufferUtils.toInputStream(body)
            FileUtils.writeFile(fileInputStream, outputFilePath.toFile())
        }




    }
}
