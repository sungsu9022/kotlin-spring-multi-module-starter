package com.starter.admin.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.starter.core.common.serializer.LocalDateTimeDeserializer
import com.starter.core.common.serializer.LocalDateTimeSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import java.time.LocalDateTime

@Configuration
class ApplicationConfig {

	@Bean
	fun objectMapper(): ObjectMapper {
		val objectMapper = Jackson2ObjectMapperBuilder.json()
				.featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
				.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
				.build<ObjectMapper>()
		objectMapper.registerModule(JavaTimeModule())
		objectMapper.registerModule(localDateTimeModule())
		return objectMapper
	}

	@Bean(name = ["localDateTimeModule"])
	fun localDateTimeModule(): SimpleModule? {
		val module = SimpleModule()
		module.addSerializer(LocalDateTime::class.java, LocalDateTimeSerializer())
		module.addDeserializer(LocalDateTime::class.java, LocalDateTimeDeserializer())
		return module
	}
}
