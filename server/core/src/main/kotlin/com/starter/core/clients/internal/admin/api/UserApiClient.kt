package com.starter.core.clients.internal.admin.api

import com.starter.core.clients.common.resolver.ModelAttributeArgumentResolver
import com.starter.core.models.user.UserCreateRequest
import com.starter.core.models.user.UserPatchRequest
import com.starter.core.models.user.UserResponse
import com.starter.core.models.user.UserSearchRequest
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.annotation.DeleteExchange
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange
import org.springframework.web.service.annotation.PatchExchange
import org.springframework.web.service.annotation.PostExchange
import org.springframework.web.service.invoker.HttpServiceProxyFactory

@HttpExchange("/api/v1/users")
interface UserApiClient : UserApi {

    companion object {
        fun create(webClient: WebClient): UserApiClient {
            val httpServiceProxyFactory = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(webClient))
                .customArgumentResolver(
                    ModelAttributeArgumentResolver.INSTANCE_WITH_JSON
                )
                .build()
            return httpServiceProxyFactory.createClient(UserApiClient::class.java)
        }
    }


    @PostExchange
    override fun create(@RequestBody request: UserCreateRequest): UserResponse

    @GetExchange
    override fun getUsers(@RequestAttribute request: UserSearchRequest) : List<UserResponse>

    @GetExchange("/{uuid}")
    override fun getUser(@PathVariable uuid: String): UserResponse

    @PatchExchange("/{uuid}")
    override fun patchUser(
        @PathVariable uuid: String,
        @RequestBody request: UserPatchRequest
    )

    @DeleteExchange("/{uuid}")
    override fun deleteUser(@PathVariable uuid: String)
}
