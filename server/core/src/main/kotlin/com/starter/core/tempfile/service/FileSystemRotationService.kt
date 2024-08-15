package com.starter.core.tempfile.service

import com.starter.core.common.utils.DateTimeFormatUtils
import com.starter.core.common.utils.DateTimeFormatUtils.yyyyMMddhhmmssnnn_FORMATTER
import mu.KLogging
import java.io.File
import java.nio.file.Path
import java.time.LocalDateTime

interface FileSystemRotationService {
    companion object : KLogging() {
        private const val DEFAULT_DIR_DELETE_HOURS = 1L
        private val DEFAULT_FILE_CREATED_AT_REG_EXP: Regex = "${TempFileSystemRotationService.DEFAULT_PRIVATE_PATH}.+_(\\d{14}).*".toRegex()
    }

    fun getRotationTargetPath(): Path

    fun deleteDir(
        fileCreatedAtRegExp : Regex = DEFAULT_FILE_CREATED_AT_REG_EXP,
        hours: Long = DEFAULT_DIR_DELETE_HOURS
    ) {
        deleteDir(
            dirPath = getRotationTargetPath(),
            fileCreatedAtRegExp = fileCreatedAtRegExp,
            hours = hours
        )
    }

    fun deleteDir(
        dirPath: Path,
        fileCreatedAtRegExp : Regex = DEFAULT_FILE_CREATED_AT_REG_EXP,
        hours: Long = DEFAULT_DIR_DELETE_HOURS
    ) {
        val targetDateTime = LocalDateTime.now().minusHours(hours)
        logger.info { "[DELETE_DIR] Start delete files : targetDatetime : $targetDateTime" }

        val targetDirDateTime = DateTimeFormatUtils.formatString(targetDateTime, yyyyMMddhhmmssnnn_FORMATTER).toLong()

        dirPath
            .toFile()
            .listFiles()
            .forEach {
                checkAndDeleteFiles(
                    file = it,
                    fileCreatedAtRegExp = fileCreatedAtRegExp,
                    targetDirDateTime = targetDirDateTime
                )
            }
    }

    fun checkAndDeleteFiles(
        file: File,
        fileCreatedAtRegExp: Regex,
        targetDirDateTime: Long
    ) {
        val fileCreatedDateTime = findFileCreatedAtFromName(file, fileCreatedAtRegExp)
        fileCreatedDateTime
            ?.let {
                if(isExpiredPeriod(it, targetDirDateTime)) {
                    deleteDirRecursively(file)
                }
            }
        }


    private fun findFileCreatedAtFromName(
        file: File,
        fileCreatedAtRegExp: Regex
    ): Long? {
        return fileCreatedAtRegExp.find(file.path)
            ?.groupValues
            ?.get(1)
            ?.toLong()
    }

    private fun isExpiredPeriod(fileDateTime: Long, targetDirDateTime: Long): Boolean {
        return fileDateTime < targetDirDateTime
    }

    fun deleteDirRecursively(dir: File) {
        dir.deleteRecursively()
    }


}
