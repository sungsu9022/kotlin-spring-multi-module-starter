package com.starter.core.rdb.domain.file.models

import com.starter.core.common.exception.ErrorCode
import com.starter.core.common.exception.StarterException
import org.apache.commons.io.FilenameUtils
import org.springframework.http.MediaType
import org.springframework.web.multipart.MultipartFile

enum class FileFormat(
    val extensions: Set<String>,
    val maxSize: Int,
) {
    IMAGE(setOf("png", "jpg", "jpeg", "gif", "ai"), 1000),
    VIDEO(setOf("mp4"), 10000),
    XML(setOf("xml"), 1000),
    EXCEL(setOf("xlsx", "xls"), 10000),
    WORD(setOf("doc", "docx"), 10000),
    PPT(setOf("ppt", "pptx"), 10000),
    PDF(setOf("pdf"), 10000),
    TXT(setOf("txt"), 1000),
    CSV(setOf("csv"), 1000),
    TSV(setOf("tsv"), 1000),
    ZIP(setOf("zip"), 10000),
    ETC(setOf(""), 1000),
    ;

    val extension: String
        get() = extensions.first()

    companion object {
        private const val MEGA_BYTE: Double = (1024 * 1024).toDouble()
        private const val DEFAULT_MIME_TYPES = MediaType.APPLICATION_OCTET_STREAM_VALUE
        const val APPLICATION_ZIP_VALUE = "application/zip"
        private const val IMAGE_JPG_VALUE = "image/jpg"
        private const val VIDEO_MP4_VALUE = "video/mp4"
        private const val MS_WORD_VALUE = "application/msword"
        private const val TIKA_OOXML_VALUE = "application/x-tika-ooxml"
        private const val CSV_VALUE = "text/csv"
        private const val APPLICATION_XML_VALUE = "application/xml"

        private val ABLE_MIME_TYPES =
            hashSetOf(
                MediaType.IMAGE_GIF_VALUE,
                MediaType.IMAGE_JPEG_VALUE,
                MediaType.IMAGE_PNG_VALUE,
                IMAGE_JPG_VALUE,
                VIDEO_MP4_VALUE,
                "video/quicktime",
                MediaType.APPLICATION_PDF_VALUE,
                MS_WORD_VALUE,
                TIKA_OOXML_VALUE,
                CSV_VALUE,
                MediaType.TEXT_PLAIN_VALUE,
                APPLICATION_ZIP_VALUE,
                APPLICATION_XML_VALUE,
            )

        private val CONTENT_TYPE_MAP =
            mapOf(
                "png" to MediaType.IMAGE_PNG_VALUE,
                "jpg" to IMAGE_JPG_VALUE,
                "gif" to MediaType.IMAGE_GIF_VALUE,
                "jpeg" to MediaType.IMAGE_JPEG_VALUE,
                "mp4" to VIDEO_MP4_VALUE,
                "pdf" to MediaType.APPLICATION_PDF_VALUE,
                "doc" to MS_WORD_VALUE,
                "xls" to TIKA_OOXML_VALUE,
                "xlsx" to TIKA_OOXML_VALUE,
                "pptx" to TIKA_OOXML_VALUE,
                "docx" to TIKA_OOXML_VALUE,
                "csv" to CSV_VALUE,
                "xml" to APPLICATION_XML_VALUE,
                "zip" to APPLICATION_ZIP_VALUE,
            )

        fun of(fileName: String): FileFormat {
            val extension = FilenameUtils.getExtension(fileName)

            return find(extension) ?: ETC
        }

        fun of(file: MultipartFile): FileFormat {
            val extension = FilenameUtils.getExtension(file.originalFilename)

            val fileFormat = find(extension) ?: ETC
            val fileSizeMegaByte = file.size / MEGA_BYTE
            if (fileSizeMegaByte > fileFormat.maxSize) {
                throw StarterException(ErrorCode.UN_SUPPORT, "허용된 파일 크기를 초과하였습니다. [ fileFormat : $fileFormat, fileSizeMB : $fileSizeMegaByte ] ")
            }

            return fileFormat
        }

        private fun find(extension: String): FileFormat? =
            entries
                .find { it.extensions.contains(extension.lowercase()) }
    }

    fun getContentType(): String {
        return CONTENT_TYPE_MAP[this.extension] ?: DEFAULT_MIME_TYPES
    }




}
