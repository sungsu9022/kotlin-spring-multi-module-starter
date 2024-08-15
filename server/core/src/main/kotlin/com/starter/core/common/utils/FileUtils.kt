package com.starter.core.common.utils

import mu.KLogging
import java.io.File
import java.io.InputStream
import java.io.OutputStream
import java.time.LocalDateTime

object FileUtils : KLogging() {

    fun writeFile(
        inputStream: InputStream,
        outputFile: File
    ) {
        val start = LocalDateTime.now()
        logger.debug { "[WRITE_FILE][${outputFile.name}] start : $start"  }
        inputStream.use { input ->
            outputFile.outputStream().use { output ->
                input.copyTo(output)
            }
        }
        val end = LocalDateTime.now()
        logger.debug { "[WRITE_FILE][${outputFile.name}] end : $end"  }
    }

    fun write(
        inputStream: InputStream,
        outputStream: OutputStream
    ) {
        inputStream.use { input ->
            outputStream.use { output ->
                input.copyTo(output)
            }
        }
    }
}
