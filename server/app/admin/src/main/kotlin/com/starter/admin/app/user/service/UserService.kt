package com.starter.admin.app.user.service

import com.starter.core.common.exception.ErrorCode
import com.starter.core.common.exception.StarterException
import com.starter.core.rdb.domain.user.models.UserMapper
import com.starter.core.rdb.domain.user.models.UserVO
import com.starter.core.rdb.domain.user.repository.UserEntity
import com.starter.core.rdb.domain.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val repository: UserRepository,
    private val mapper: UserMapper
) {

    fun getByUuid(uuid: String): UserVO {
        return repository.findByUuid(uuid)
            ?.let { mapper.convertToTarget(it) }
            ?: throw StarterException(ErrorCode.NOT_FOUND)
    }

    fun getUsersByName(userName: String): List<UserVO> {
        return repository.findAllByName(userName)
            .map { mapper.convertToTarget(it) }
    }

    @Transactional
    fun save(userEntity: UserEntity): UserVO {
        val saved = repository.save(userEntity)
        return mapper.convertToTarget(saved)
    }
}
