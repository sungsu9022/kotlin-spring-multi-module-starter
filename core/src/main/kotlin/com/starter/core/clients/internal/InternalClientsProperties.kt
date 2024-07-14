package com.starter.core.clients.internal

import org.springframework.boot.context.properties.ConfigurationProperties


@ConfigurationProperties("clients.internal")
data class InternalClientsProperties(
    val file: String,
)
