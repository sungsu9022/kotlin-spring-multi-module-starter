package com.starter.core.rdb.domain.file.repository

import org.springframework.data.jpa.repository.JpaRepository

interface FileRepository : JpaRepository<File, Long>
