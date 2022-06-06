package com.starter.core.common.querydsl

import com.starter.core.config.DataSourceConfig
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

abstract class AbstractQuerydslRepositorySupport(domainClass: Class<*>?) : QuerydslRepositorySupport(domainClass) {
	@PersistenceContext(unitName = DataSourceConfig.PERSISTENCE_UNIT)
	override fun setEntityManager(entityManager: EntityManager) {
		super.setEntityManager(entityManager)
	}

}
