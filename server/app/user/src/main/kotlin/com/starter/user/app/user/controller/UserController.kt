package com.starter.user.app.user.controller

import com.starter.core.rdb.domain.user.models.UserVO
import com.starter.user.app.user.facade.UserReadFacade
import com.starter.user.app.user.facade.UserResisterFacade
import com.starter.user.app.user.model.UserCreateRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val resisterFacadeService: UserResisterFacade,
    private val readFacadeService: UserReadFacade,
) {
    @PostMapping("/api/users")
    fun createMember(
        @RequestBody userCreateRequest: UserCreateRequest,
    ): UserVO {
        val user = resisterFacadeService.resisterMember(userCreateRequest)
        return user
    }

    @GetMapping("/api/users/{uuid}")
    fun getUsers(
        @PathVariable uuid: String,
    ): UserVO {
        val user = readFacadeService.getUser(uuid)
        return user
    }

    @GetMapping("/api/users/name")
    fun getMembersByName(
        @RequestParam name: String,
    ) : List<UserVO> {
        val users = readFacadeService.getUsers(name)
        return users
    }
}
