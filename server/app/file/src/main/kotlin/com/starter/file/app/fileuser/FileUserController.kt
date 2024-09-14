package com.starter.file.app.fileuser

import com.starter.core.clients.internal.admin.api.UserApi
import com.starter.core.clients.internal.admin.api.UserApiClient
import com.starter.core.models.user.UserCreateRequest
import com.starter.core.models.user.UserPatchRequest
import com.starter.core.models.user.UserResponse
import com.starter.core.models.user.UserSearchRequest
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/file-users")
class FileUserController(
    private val userApiClient: UserApiClient,
) : UserApi {
    override fun create(request: UserCreateRequest): UserResponse {
        return userApiClient.create(request)
    }

    @GetMapping
    override fun getUsers(request: UserSearchRequest): List<UserResponse> {
        return userApiClient.getUsers(request)
    }

    @GetMapping("/{uuid}")
    override fun getUser(@PathVariable uuid: String): UserResponse {
        return userApiClient.getUser(uuid)
    }

    @PatchMapping("/{uuid}")
    override fun patchUser(
        @PathVariable uuid: String,
        @RequestBody request: UserPatchRequest
    ) {
        return userApiClient.patchUser(uuid, request)
    }

    @DeleteMapping("/{uuid}")
    override fun deleteUser(@PathVariable uuid: String) {
        return userApiClient.deleteUser(uuid)
    }


}
