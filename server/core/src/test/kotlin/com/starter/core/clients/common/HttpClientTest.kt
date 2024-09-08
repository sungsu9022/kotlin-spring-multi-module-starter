package com.starter.core.clients.common

import io.kotest.core.spec.style.AnnotationSpec
import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.client.WebClient
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse


class HttpClientTest : AnnotationSpec() {
    companion object : KLogging()

    @Test
    fun httpClientTest() {
        val client = HttpClient.newBuilder().build()

        val request = HttpRequest.newBuilder()
            .uri(URI.create("https://www.naver.com"))
            .GET()
            .build()

        val response : HttpResponse<String> = client.send(request, HttpResponse.BodyHandlers.ofString())
        // 추가적으로 Response Model에 대한 Deserialize 필요
        logger.info { "response : ${response.body()}" }
    }

    @Test
    fun restTemplateTest() {
        val restTemplate = RestTemplate()
        val url = "https://www.naver.com"
        val response = restTemplate.getForEntity(url, String::class.java)

        if (response.statusCode === HttpStatus.OK) {
            val responseBody = response.body
            logger.info { "response : $responseBody" }
        }
    }

    @Test
    fun webClientTest() {
        val webClient = WebClient.create("https://www.naver.com")

        val response = webClient.get()
            .uri("/data")
            .retrieve()
            .bodyToMono(String::class.java)

        response.subscribe { logger.info { it } }
    }
}



