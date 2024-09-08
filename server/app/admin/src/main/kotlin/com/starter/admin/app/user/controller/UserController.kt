package com.starter.admin.app.user.controller

import com.starter.admin.app.user.facade.UserModifyFacade
import com.starter.admin.app.user.facade.UserReadFacade
import com.starter.core.clients.internal.admin.api.UserApi
import com.starter.core.models.user.UserCreateRequest
import com.starter.core.models.user.UserPatchRequest
import com.starter.core.models.user.UserResponse
import com.starter.core.models.user.UserSearchRequest
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val modifyFacade: UserModifyFacade,
    private val readFacade: UserReadFacade,
) : UserApi {

    @PostMapping
    override fun create(
        @RequestBody request: UserCreateRequest,
    ): UserResponse {
        val user = modifyFacade.create(request)
        return user
    }

    @GetMapping
    override fun getUsers(
        request: UserSearchRequest,
    ) : List<UserResponse> {
        return readFacade.getUsers(request)
    }

    @GetMapping("/{uuid}")
    override fun getUser(
        @PathVariable uuid: String,
    ): UserResponse {
        val user = readFacade.getUser(uuid)
        return user
    }

    @PatchMapping("/{uuid}")
    override fun patchUser(
        @PathVariable uuid: String,
        @RequestBody request: UserPatchRequest,
    ) {
        request.validate()
        modifyFacade.patch(uuid, request)
    }

    @DeleteMapping("/{uuid}")
    override fun deleteUser(
        @PathVariable uuid: String,
    ) {
        modifyFacade.delete(uuid)
    }
}
