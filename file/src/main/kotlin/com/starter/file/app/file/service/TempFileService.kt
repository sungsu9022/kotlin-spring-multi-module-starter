package com.starter.file.app.file.service

import com.starter.core.common.utils.DateTimeFormatUtils
import com.starter.file.config.FileProperty
import mu.KLogging
import org.springframework.stereotype.Service
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.time.LocalDateTime

@Service
class TempFileService(
    private val fileProperty: FileProperty,
) {
    companion object : KLogging()

    fun createTempFilePath(id: Long = 0): Path {
        val now = LocalDateTime.now()
        val tempDirVariable = DateTimeFormatUtils.formatString(now)
        val tempFileDirectoryName = "${id}_${tempDirVariable}"

        val tempDirPath = Paths.get(fileProperty.tempFilePath)
            .resolve(tempFileDirectoryName)

        Files.createDirectory(tempDirPath)

        return tempDirPath
    }

    fun createDirectoryPath(path: String): Path {
        val directoryPath = Paths.get(path)

        if(!Files.exists(directoryPath)) {
            Files.createDirectory(directoryPath)
        }

        return directoryPath
    }
}
