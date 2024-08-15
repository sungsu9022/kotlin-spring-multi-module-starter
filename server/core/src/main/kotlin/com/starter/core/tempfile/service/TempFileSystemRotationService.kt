package com.starter.core.tempfile.service

import com.starter.core.common.utils.DateTimeFormatUtils
import com.starter.core.common.utils.DateTimeFormatUtils.yyyyMMddhhmmssnnn_FORMATTER
import com.starter.core.tempfile.config.TempFileProperty
import mu.KLogging
import org.springframework.stereotype.Component
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.time.LocalDateTime

@Component
class TempFileSystemRotationService(
    private val tempFileProperty: TempFileProperty,
) : FileSystemRotationService{
    companion object : KLogging() {
        const val DEFAULT_PRIVATE_PATH = "tmp_"
    }

    fun getRootPath(): Path {
        val rootPath = Paths.get(tempFileProperty.path)
        Files.createDirectories(rootPath)
        return rootPath
    }

    fun getPrivatePath(prefix: String = DEFAULT_PRIVATE_PATH): Path {
        val now = LocalDateTime.now()
        val fileNameSuffix = DateTimeFormatUtils.formatString(now, yyyyMMddhhmmssnnn_FORMATTER)
        val privatePath = getRootPath().resolve("${prefix}_$fileNameSuffix")
        Files.createDirectories(privatePath)
        return privatePath
    }

    fun getStaticPath(prefix: String = DEFAULT_PRIVATE_PATH): Path {
        val privatePath = getRootPath().resolve(prefix)
        Files.createDirectories(privatePath)
        return privatePath
    }

    override fun getRotationTargetPath(): Path {
        return getRootPath()
    }
}
