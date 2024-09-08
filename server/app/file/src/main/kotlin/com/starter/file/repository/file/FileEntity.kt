package com.starter.file.repository.file

import com.starter.core.models.file.File
import com.starter.core.models.file.FileFormat
import com.starter.core.models.file.FileStorageType
import com.starter.core.models.file.FileType
import com.starter.core.rdb.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.annotations.DynamicUpdate

@Entity(name = "file")
@DynamicUpdate
class FileEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override val id: Long = 0,
    @Column(columnDefinition = "CHAR(36)")
    override val fileUuid: String,
    override val filePath: String,
    @Column(name = "file_storage_type", columnDefinition = "varchar")
    @Enumerated(EnumType.STRING)
    override val fileStorageType: FileStorageType,
    override val fileName: String,
    override val fileSize: Long,
    @Column(columnDefinition = "varchar")
    @Enumerated(EnumType.STRING)
    override val fileFormat: FileFormat,
    @Column(name = "file_type", columnDefinition = "varchar")
    @Enumerated(EnumType.STRING)
    override val fileType: FileType,
    @Column(name = "is_deleted", columnDefinition = "TINYINT(1)")
    override val deleted: Boolean = false,
) : BaseEntity(), File

