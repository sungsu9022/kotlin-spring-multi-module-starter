package com.starter.core.member.repository

import com.starter.core.common.entity.BaseEntity
import com.starter.core.member.models.Member
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table


@Entity
@Table(name = "member")
class MemberEntity(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val no: Long = 0,
	override val id: String,
	override var name: String,
) : BaseEntity(), Member
