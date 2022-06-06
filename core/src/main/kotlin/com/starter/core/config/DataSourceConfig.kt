package com.starter.core.config

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(basePackages = ["com.starter"])
@EntityScan("com.starter")
@EnableTransactionManagement
@EnableJpaAuditing
class DataSourceConfig(
	private val jpaProperties: JpaProperties,
	private val hibernateProperties: HibernateProperties
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
	fun entityManagerFactory(
		builder: EntityManagerFactoryBuilder,
		dataSource: DataSource,

	): LocalContainerEntityManagerFactoryBean {
		val properties = hibernateProperties.determineHibernateProperties(jpaProperties.properties, HibernateSettings())

		return builder.dataSource(dataSource)
			.packages("com.starter")
			.properties(properties)
			.persistenceUnit(PERSISTENCE_UNIT)
			.build()
	}

	@Bean
	fun transactionManager(entityManagerFactory: EntityManagerFactory): PlatformTransactionManager {
		val txManager = JpaTransactionManager()
		txManager.setEntityManagerFactory(entityManagerFactory)
		return txManager
	}

	@Bean
	fun jpaQueryFactory(entityManager: EntityManager): JPAQueryFactory {
		return JPAQueryFactory(entityManager)
	}

	@Bean
	fun pageableCustomizer() : PageableHandlerMethodArgumentResolverCustomizer {
		return PageableHandlerMethodArgumentResolverCustomizer { resolver ->
			resolver.setOneIndexedParameters(true)
			resolver.setMaxPageSize(100)
		}
	}
}
