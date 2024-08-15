package com.starter.dbmigration

import com.starter.core.jasypt.config.JasyptConfig
import com.starter.core.rdb.config.DataSourceConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@ConfigurationPropertiesScan
@SpringBootApplication
@Import(
    JasyptConfig::class,
    DataSourceConfig::class
)
class DbMigrationApplication

fun main(args: Array<String>) {
    runApplication<DbMigrationApplication>(*args)
}
