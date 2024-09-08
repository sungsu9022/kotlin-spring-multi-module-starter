package com.starter.admin.app.user.facade

import com.starter.admin.app.user.mapper.UserModelMapper
import com.starter.admin.service.user.UserService
import com.starter.core.models.user.UserResponse
import com.starter.core.models.user.UserSearchRequest
import mu.KLogging
import org.springframework.stereotype.Component

@Component
class UserReadFacade(
    private val userService: UserService,
    private val modelMapper: UserModelMapper,
) {
    companion object : KLogging()

    fun getUser(uuid: String): UserResponse = userService.getByOrThrow(uuid)
        .let { modelMapper.toResponse(it) }

    fun getUsers(request: UserSearchRequest): List<UserResponse> {
        val condition = modelMapper.toCondition(request)
        return userService.getAllBy(condition)
            .map { modelMapper.toResponse(it) }
    }
}
