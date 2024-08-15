package com.starter.file.app.file.service

import com.starter.core.common.utils.FilePathUtils
import com.starter.core.common.utils.FileUtils
import com.starter.core.common.utils.UUIDUtils
import com.starter.core.rdb.domain.file.models.FileStorageType
import com.starter.file.app.file.models.FileUploadResult
import com.starter.file.app.file.models.InputStreamFileMetadata
import com.starter.file.config.FileProperty
import mu.KLogging
import org.springframework.core.io.InputStreamResource
import org.springframework.stereotype.Service
import java.io.FileInputStream
import java.nio.file.Paths
import java.time.LocalDate


@Service
class LocalFileSystemService(
    private val fileProperty: FileProperty,
) : FileSystemService {
    companion object : KLogging()


    override fun upload(metadata: InputStreamFileMetadata): FileUploadResult {
        val today = LocalDate.now()
        val uploadDirPath = FilePathUtils.createDirPath(
            rootPath = fileProperty.uploadPath,
            subPath = "${today.year}/${today.monthValue}/${today.dayOfMonth}"
        )

        val storageFileName = "${UUIDUtils.generate()}.${metadata.fileExtension}"
        val outFile = uploadDirPath
            .resolve(storageFileName)
            .toFile()

        FileUtils.writeFile(
            inputStream = metadata.inputStream,
            outputFile = outFile
        )

        return FileUploadResult(
            storageType = FileStorageType.LOCAL_FILE_SYSTEM,
            storageKey = outFile.path
        )
    }

    override fun get(key: String): InputStreamResource {
        val file = Paths.get(key).toFile()
        val fileInputStream = FileInputStream(file)
        return InputStreamResource(fileInputStream)
    }
}
