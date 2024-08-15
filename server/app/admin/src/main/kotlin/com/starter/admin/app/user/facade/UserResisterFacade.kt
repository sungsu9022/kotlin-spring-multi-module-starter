package com.starter.admin.app.user.facade

import com.starter.admin.app.user.mapper.UserModelMapper
import com.starter.admin.app.user.model.UserCreateRequest
import com.starter.admin.app.user.service.UserService
import com.starter.core.common.utils.UUIDUtils
import com.starter.core.rdb.domain.user.models.UserVO
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class UserResisterFacade(
    private val userService: UserService,
    private val userModelMapper: UserModelMapper,
) {
    companion object {
        private val logger = KotlinLogging.logger {}
    }

    @Transactional
    fun resisterMember(userCreateRequest: UserCreateRequest): UserVO {
        val uuid = UUIDUtils.generate()
        val user = userModelMapper.convertToEntity(userCreateRequest, uuid)
        val saved = userService.save(user)
        logger.debug { "saved : $saved" }
        return saved
    }
}
