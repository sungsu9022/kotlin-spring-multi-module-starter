package com.starter.file.app.file.models

enum class UploadDirectoryType(val path: String) {
    TEMPORARY("temporary"),
    REPOSITORY("repository"),
    ;

    companion object {
        fun from(fileKey: String): UploadDirectoryType? =
            UploadDirectoryType
                .values()
                .find { fileKey.startsWith(it.path) }
    }
}
