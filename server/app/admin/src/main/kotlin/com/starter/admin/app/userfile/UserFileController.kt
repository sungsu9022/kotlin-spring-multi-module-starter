package com.starter.admin.app.userfile

import com.starter.core.common.utils.FluxDataBufferUtils
import com.starter.core.models.file.FileResponse
import com.starter.core.models.file.FileUploadRequest
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.springframework.core.io.InputStreamResource
import org.springframework.core.io.Resource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/user-files")
class UserFileController(
    private val userFileFacade: UserFileFacade
) {

    @GetMapping
    fun getFile(@RequestParam fileId: Long): ResponseEntity<Resource> {
        return  userFileFacade.getFile(fileId)
    }

    @GetMapping("/data-buffers")
    fun getFileDataBuffer(@RequestParam fileId: Long): ResponseEntity<Resource> {
        val fileDataBuffer = userFileFacade.getFileDataBuffer(fileId)
        val fileInputStream = FluxDataBufferUtils.toInputStream(fileDataBuffer.body!!)
        return ResponseEntity.ok()
            .headers(fileDataBuffer.headers)
            .body(InputStreamResource(fileInputStream))
    }

    @GetMapping("/write-local")
    fun writeLocalFile(@RequestParam fileId: Long)  {
        GlobalScope.launch {
            userFileFacade.writeLocal(fileId)
        }
    }

    @PostMapping("/upload")
    fun uploadFile(request: FileUploadRequest): FileResponse {
        return userFileFacade.upload(request)
    }

}
