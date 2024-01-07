package com.starter.file

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = ["com.starter.file", "com.starter.core"],
)
class FileApplication

fun main(args: Array<String>) {
    runApplication<FileApplication>(*args)
}
