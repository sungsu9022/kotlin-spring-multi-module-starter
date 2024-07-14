package com.starter.core.clients.common

import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import reactor.netty.resources.ConnectionProvider
import java.time.Duration


object WebClientFactory {
    private const val MEGA_BYTE = 1024 * 1024
    private const val DEFAULT_MAX_MEMORY_SIZE = 1024 * MEGA_BYTE


    fun createNettyClient(
        baseUrl: String
    ): WebClient {
        val provider: ConnectionProvider = ConnectionProvider.builder("custom")
            .maxConnections(100)
            .pendingAcquireTimeout(Duration.ofSeconds(60))
            .pendingAcquireMaxCount(-1)
            .build()

        val httpClient: HttpClient = HttpClient.create(provider)
            .wiretap(true)
            .responseTimeout(Duration.ofSeconds(10))
            .doOnConnected { conn ->
                conn.addHandlerLast(ReadTimeoutHandler(10))
                    .addHandlerLast(WriteTimeoutHandler(10))
            }
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            .option(ChannelOption.SO_RCVBUF, 1048576)
            .option(ChannelOption.SO_SNDBUF, 1048576)
//            .doOnRequest { req, conn ->
//                conn.addHandlerLast(LoggingHandler("REQUEST_LOG", LogLevel.INFO))
//            }
//            .doOnResponse { res, conn ->
//                conn.addHandlerLast(LoggingHandler("RESPONSE_LOG", LogLevel.INFO))
//            }

        return WebClient.builder()
            .clientConnector(ReactorClientHttpConnector(httpClient))
            .codecs { configurer ->
                configurer.defaultCodecs().maxInMemorySize(DEFAULT_MAX_MEMORY_SIZE)
            }
            .baseUrl(baseUrl)
            .build()
    }
}
