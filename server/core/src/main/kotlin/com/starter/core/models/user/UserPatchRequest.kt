package com.starter.core.models.user

import com.fasterxml.jackson.annotation.JsonIgnore
import com.starter.core.common.exception.ErrorCode
import com.starter.core.common.exception.StarterException

data class UserPatchRequest(
    val email: String?,
    val userName: String?,
    val userStatus: UserStatus?,
) {

    @get:JsonIgnore
    val emptyRequest: Boolean
        get() = email == null
            && userName == null
            && userStatus == null

    fun validate() {
        if(emptyRequest) {
            throw StarterException(ErrorCode.INVALID_PARAMETER, "1개 이상 변경이 필요합니다. ( request : $this )")
        }
    }
}
