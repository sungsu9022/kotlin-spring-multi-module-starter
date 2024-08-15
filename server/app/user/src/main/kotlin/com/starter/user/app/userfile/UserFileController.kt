package com.starter.user.app.userfile

import com.starter.core.common.utils.FluxDataBufferUtils
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.springframework.core.io.InputStreamResource
import org.springframework.core.io.Resource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class UserFileController(
    private val userFileFacade: UserFileFacade
) {

    @GetMapping("/api/user-files")
    fun getFile(@RequestParam fileId: Long): ResponseEntity<Resource> {
        return  userFileFacade.getFile(fileId)
    }

    @GetMapping("/api/user-files/data-buffers")
    fun getFileDataBuffer(@RequestParam fileId: Long): ResponseEntity<Resource> {
        val fileDataBuffer = userFileFacade.getFileDataBuffer(fileId)
        val fileInputStream = FluxDataBufferUtils.toInputStream(fileDataBuffer.body!!)
        return ResponseEntity.ok()
            .headers(fileDataBuffer.headers)
            .body(InputStreamResource(fileInputStream))
    }

    @GetMapping("/api/user-files/write-local")
    fun writeLocalFile(@RequestParam fileId: Long)  {
        GlobalScope.launch {
            userFileFacade.writeLocal(fileId)
        }
    }

}
