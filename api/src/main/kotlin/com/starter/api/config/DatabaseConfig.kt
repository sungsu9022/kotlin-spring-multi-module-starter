package com.starter.api.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(basePackages = ["com.starter"])
@EntityScan("com.starter")
@EnableTransactionManagement
@EnableJpaAuditing
class DatabaseConfig(
	private val jpaProperties: JpaProperties,
) {
	companion object {
		const val PERSISTENCE_UNIT = "entityManager"
	}

	@Profile("local")
	@Bean
	fun dataSource(): DataSource {
		val builder = EmbeddedDatabaseBuilder()
		return builder.setType(EmbeddedDatabaseType.H2)
			.build()
	}

	@Bean
	fun transactionManager(entityManagerFactory: EntityManagerFactory): PlatformTransactionManager {
		val txManager = JpaTransactionManager()
		txManager.setEntityManagerFactory(entityManagerFactory)
		return txManager
	}
}