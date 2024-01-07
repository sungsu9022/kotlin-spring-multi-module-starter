package com.starter.core.rdb.domain.file.repository

import com.starter.core.rdb.domain.file.models.FileExtensionType
import com.starter.core.rdb.domain.file.models.FileType
import com.starter.core.rdb.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class File(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(columnDefinition = "CHAR(36)")
    val fileUuid: String,
    val filePath: String,
    val fileName: String,
    val fileSize: Int,
    @Column(name = "file_extension_enum", columnDefinition = "varchar")
    @Enumerated(EnumType.STRING)
    val fileExtensionType: FileExtensionType,
    @Column(name = "file_type_enum", columnDefinition = "varchar")
    @Enumerated(EnumType.STRING)
    val fileType: FileType,
    @Column(name = "is_deleted", columnDefinition = "TINYINT(1)")
    val deleted: Boolean = false,
) : BaseEntity()
