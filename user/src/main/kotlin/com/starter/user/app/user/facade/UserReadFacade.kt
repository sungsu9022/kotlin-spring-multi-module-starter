package com.starter.user.app.user.facade

import com.starter.core.rdb.domain.user.models.UserVO
import com.starter.user.app.user.service.UserService
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class UserReadFacade(
    private val userService: UserService,
) {
    companion object {
        private val logger = KotlinLogging.logger {}
    }

    fun getUser(uuid: String): UserVO = userService.getByUuid(uuid)

    fun getUsers(name: String): List<UserVO> = userService.getUsersByName(name)
}