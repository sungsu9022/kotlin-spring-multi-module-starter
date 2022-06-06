package com.starter.core.member.repository

import com.starter.core.common.entity.BaseEntity
import com.starter.core.member.models.Member
import javax.persistence.*


@Entity
@Table(name = "member")
class MemberEntity(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val no: Long? = null,
	override val id: String,
	override var name: String,
) : BaseEntity(), Member
