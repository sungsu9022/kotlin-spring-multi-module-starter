package com.starter.file.repository.file

import org.springframework.data.jpa.repository.JpaRepository

interface FileRepository : JpaRepository<FileEntity, Long>
