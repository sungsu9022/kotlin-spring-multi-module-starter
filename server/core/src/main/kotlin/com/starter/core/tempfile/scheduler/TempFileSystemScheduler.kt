package com.starter.core.tempfile.scheduler

import com.starter.core.tempfile.service.TempFileSystemProcessor
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class TempFileSystemScheduler(
    private val tempFileSystemProcessor: TempFileSystemProcessor,
) {

    @Scheduled(cron ="0 0 */1 * * *")
    fun hourlyDeleteLocalFiles() {
        tempFileSystemProcessor.deleteDir()
    }
}
