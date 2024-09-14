package com.starter.core.models.file

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
