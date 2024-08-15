package com.starter.core.common.utils

import org.eclipse.jetty.http.HttpHeader
import org.springframework.http.MediaType

object HttpHeaderUtils {

    fun makeFileDownloadHeaders(fileName: String, fileSize: Long): org.springframework.http.HttpHeaders {
        val headers = org.springframework.http.HttpHeaders()
        headers.add(HttpHeader.CONTENT_DISPOSITION.toString(), "attachment; filename=$fileName")
        headers.contentLength = fileSize
        headers.contentType = MediaType.APPLICATION_OCTET_STREAM
        headers.add("Content-Transfer-Encoding","binary")

        return headers
    }
}
