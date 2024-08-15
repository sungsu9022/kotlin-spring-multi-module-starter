package com.starter.file.app.file.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/files")
class FileController(
) {

    @GetMapping
    fun getFiles(fileId: Long): String {
        return ""
    }
}
