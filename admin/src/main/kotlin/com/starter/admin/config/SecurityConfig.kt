package com.starter.admin.config

import com.starter.admin.config.property.SecurityProperty
import org.apache.commons.lang3.StringUtils
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import java.time.Duration

@Configuration
@EnableWebSecurity
class SecurityConfig(private val securityProperty : SecurityProperty) : WebSecurityConfigurerAdapter() {
	companion object {
		val CORS_ALLOWED_METHODS = listOf(
			HttpMethod.GET.name,
			HttpMethod.POST.name,
			HttpMethod.PATCH.name,
			HttpMethod.PUT.name,
			HttpMethod.DELETE.name,
			HttpMethod.OPTIONS.name
		)
	}


	override fun configure(http: HttpSecurity) {
		http
			.formLogin().disable()
			.cors().configurationSource(corsConfigurationSource())
			.and()
			.csrf().disable() // NOTE : CSRF 방어를 제거해둠. 추후 요청에 삽입하도록 수정해야함
			.authorizeRequests()
			.anyRequest().permitAll()

		http.headers().defaultsDisabled()
			.xssProtection()
			.and()
			.cacheControl()
			.and()
			.httpStrictTransportSecurity()
			.includeSubDomains(true)
			.maxAgeInSeconds(Duration.ofDays(365).seconds)
			.and()
			.referrerPolicy(ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN)
			.and()
			.contentTypeOptions()
	}

	override fun configure(web: WebSecurity) {
		val ignoreAntMatchers: MutableList<String> = ArrayList()
		val swaggerUIPath: String = securityProperty.swaggerUIPath
		if (StringUtils.isNotBlank(swaggerUIPath)) {
			ignoreAntMatchers.add(swaggerUIPath)
		}

		web.ignoring()
			.antMatchers(*ignoreAntMatchers.toTypedArray())
	}


	private fun corsConfigurationSource(): CorsConfigurationSource? {
		val corsConfiguration = CorsConfiguration()
		corsConfiguration.allowedOrigins = listOf(CorsConfiguration.ALL)
		corsConfiguration.allowedHeaders = listOf(CorsConfiguration.ALL)
		CORS_ALLOWED_METHODS.forEach { method -> corsConfiguration.addAllowedMethod(method) }
		corsConfiguration.allowCredentials = true
		corsConfiguration.setMaxAge(Duration.ofDays(1))
		val urlBasedCorsConfigurationSource = UrlBasedCorsConfigurationSource()
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration)
		return urlBasedCorsConfigurationSource
	}

}
