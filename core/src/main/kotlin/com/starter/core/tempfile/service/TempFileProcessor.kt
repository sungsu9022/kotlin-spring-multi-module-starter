package com.starter.core.tempfile.service

import com.starter.core.tempfile.config.TempFileProperty
import org.springframework.stereotype.Component
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

@Component
class TempFileProcessor(
    private val tempFileProperty: TempFileProperty,
) {

    fun getRootPath(): Path {
        val rootPath = Paths.get(tempFileProperty.path)
        Files.createDirectories(rootPath)
        return rootPath
    }

    fun getPrivatePath(prefix: String): Path {
        val privatePath = getRootPath().resolve(prefix)
        Files.createDirectories(privatePath)
        return privatePath
    }
}
