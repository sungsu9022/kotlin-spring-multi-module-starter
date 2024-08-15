package com.starter.core.rdb.domain.file.repository

import com.starter.core.rdb.domain.file.models.FileFormat
import com.starter.core.rdb.domain.file.models.FileStorageType
import com.starter.core.rdb.domain.file.models.FileType
import com.starter.core.rdb.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.annotations.DynamicUpdate

@Entity
@DynamicUpdate
class File(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(columnDefinition = "CHAR(36)")
    val fileUuid: String,
    val filePath: String,
    @Column(name = "file_storage_type", columnDefinition = "varchar")
    @Enumerated(EnumType.STRING)
    val fileStorageType: FileStorageType,
    val fileName: String,
    val fileSize: Long,
    @Column(columnDefinition = "varchar")
    @Enumerated(EnumType.STRING)
    val fileFormat: FileFormat,
    @Column(name = "file_type", columnDefinition = "varchar")
    @Enumerated(EnumType.STRING)
    val fileType: FileType,
    @Column(name = "is_deleted", columnDefinition = "TINYINT(1)")
    val deleted: Boolean = false,
) : BaseEntity()

