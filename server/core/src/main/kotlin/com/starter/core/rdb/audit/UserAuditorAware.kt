package com.starter.core.rdb.audit

import org.springframework.data.domain.AuditorAware
import org.springframework.stereotype.Component
import java.util.Optional


@Component
class UserAuditorAware : AuditorAware<String> {
    override fun getCurrentAuditor(): Optional<String> {
        // TODO
        return Optional.of("system")
    }
}
