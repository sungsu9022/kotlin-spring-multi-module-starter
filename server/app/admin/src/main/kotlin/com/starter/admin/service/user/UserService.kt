package com.starter.admin.service.user

import com.starter.admin.app.user.model.UserSearchCondition
import com.starter.admin.repository.user.UserEntity
import com.starter.admin.repository.user.UserRepository
import com.starter.core.common.exception.ErrorCode
import com.starter.core.common.exception.StarterException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserService(
    private val repository: UserRepository,
) {

    fun getByOrThrow(uuid: String): UserEntity {
        return repository.findByUuid(uuid)
            ?: throw StarterException(ErrorCode.NOT_FOUND)
    }

    fun getAllBy(condition: UserSearchCondition): List<UserEntity> {
        return repository.findAllBy(condition)
    }

    @Transactional
    fun save(userEntity: UserEntity): UserEntity {
        return repository.save(userEntity)
    }

    @Transactional
    fun delete(uuid: String) {
        repository.deleteByUuid(uuid)
    }
}
