package com.starter.admin.app.user.facade

import com.starter.admin.app.user.mapper.UserModelMapper
import com.starter.admin.service.user.UserService
import com.starter.core.common.utils.UUIDUtils
import com.starter.core.models.user.UserCreateRequest
import com.starter.core.models.user.UserPatchRequest
import com.starter.core.models.user.UserResponse
import com.starter.core.models.user.UserStatus
import mu.KLogging
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class UserModifyFacade(
    private val userService: UserService,
    private val modelMapper: UserModelMapper,
) {
    companion object : KLogging()

    @Transactional
    fun create(userCreateRequest: UserCreateRequest): UserResponse {
        val user = modelMapper.toEntity(
            request = userCreateRequest,
            uuid = UUIDUtils.generate(),
            userStatus = UserStatus.ACTIVE,
        )
        return userService.save(user)
            .let { modelMapper.toResponse(it) }
    }

    @Transactional
    fun patch(
        uuid: String,
        request: UserPatchRequest,
    ) {
        val user = userService.getByOrThrow(uuid)
        request.userName?.let { user.userName = it }
        request.email?.let { user.email = it }
        request.userStatus?.let { user.userStatus = it }
        userService.save(user)
    }

    @Transactional
    fun delete(uuid: String) {
        val user = userService.getByOrThrow(uuid)
        userService.delete(uuid)
    }
}
