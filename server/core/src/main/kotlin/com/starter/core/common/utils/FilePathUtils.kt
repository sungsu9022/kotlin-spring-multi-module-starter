package com.starter.core.common.utils

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.time.LocalDateTime

object FilePathUtils {
    private const val DEFAULT_TEMP_DIR_PREFIX = "system"

    fun createDirPath(rootPath: String): Path {
        val path = Paths.get(rootPath)
        Files.createDirectories(path)
        return path
    }

    fun createDirPath(
        rootPath: String,
        subPath: String,
    ): Path {
        val path = Paths.get(rootPath).resolve(subPath)
        Files.createDirectories(path)
        return path
    }

    fun createTempDirPath(
        rootPath: String,
        directoryPrefix: String = DEFAULT_TEMP_DIR_PREFIX,
    ): Path {
        val now = LocalDateTime.now()
        val tempDirVariable = DateTimeFormatUtils.formatString(now)
        val tempFileDirectoryName = "${directoryPrefix}_$tempDirVariable"

        val tempDirPath = Paths
            .get(rootPath)
            .resolve(tempFileDirectoryName)

        Files.createDirectory(tempDirPath)

        return tempDirPath
    }
}
