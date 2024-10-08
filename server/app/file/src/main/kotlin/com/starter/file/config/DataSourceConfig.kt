package com.starter.file.config

import com.querydsl.jpa.impl.JPAQueryFactory
import com.starter.core.rdb.config.JpaCommonConfig
import com.starter.file.config.DataSourceConfig.Companion.ENTITY_PACKAGE
import com.zaxxer.hikari.HikariDataSource
import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(basePackages = [ENTITY_PACKAGE])
@EntityScan(ENTITY_PACKAGE)
@EnableTransactionManagement
@Import(
    JpaCommonConfig::class
)
class DataSourceConfig(
    private val jpaProperties: JpaProperties,
    private val hibernateProperties: HibernateProperties,
) {
    companion object {
        const val PERSISTENCE_UNIT = "entityManager"
        const val ENTITY_PACKAGE = "com.starter.file"
    }

    @Bean
    @ConfigurationProperties("spring.datasource")
    fun dataSourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.configuration.hikari")
    fun dataSource(dataSourceProperties: DataSourceProperties): HikariDataSource {
        return dataSourceProperties.initializeDataSourceBuilder()
            .type(HikariDataSource::class.java)
            .build()
    }

    @Bean
    fun entityManagerFactory(
        builder: EntityManagerFactoryBuilder,
        dataSource: DataSource,
    ): LocalContainerEntityManagerFactoryBean {
        val properties = hibernateProperties.determineHibernateProperties(jpaProperties.properties, HibernateSettings())

        return builder.dataSource(dataSource)
            .packages(ENTITY_PACKAGE)
            .properties(properties)
            .persistenceUnit(PERSISTENCE_UNIT)
            .build()
    }

    @Bean
    fun transactionManager(entityManagerFactory: EntityManagerFactory): PlatformTransactionManager {
        val txManager = JpaTransactionManager()
        txManager.entityManagerFactory = entityManagerFactory
        return txManager
    }

    @Bean
    fun jpaQueryFactory(entityManager: EntityManager): JPAQueryFactory {
        return JPAQueryFactory(entityManager)
    }

    @Bean
    fun pageableCustomizer(): PageableHandlerMethodArgumentResolverCustomizer {
        return PageableHandlerMethodArgumentResolverCustomizer { resolver ->
            resolver.setOneIndexedParameters(true)
            resolver.setMaxPageSize(100)
        }
    }
}
