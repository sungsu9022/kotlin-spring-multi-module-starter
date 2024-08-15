package com.starter.core.common.utils

import com.starter.core.common.exception.ErrorCode
import com.starter.core.common.exception.StarterException
import mu.KLogging
import org.springframework.core.io.Resource
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.core.io.buffer.DataBufferUtils
import org.springframework.core.io.buffer.DefaultDataBufferFactory
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers
import java.io.IOException
import java.io.InputStream
import java.io.PipedInputStream
import java.io.PipedOutputStream
import java.net.MalformedURLException


object FluxDataBufferUtils : KLogging() {

    fun resourceToDataBuffer(resource: Resource): Flux<DataBuffer> {
        return try {
            if (resource.exists() || resource.isReadable) {
                return DataBufferUtils.read(resource, DefaultDataBufferFactory(), 4096)
            } else {
                throw RuntimeException("Could not read the file!");
            }
        } catch (e: MalformedURLException) {
            throw RuntimeException("Error: " + e.message)
        }
    }

    fun toInputStream(dataBuffer: Flux<DataBuffer>): InputStream {
        val osPipe = PipedOutputStream()
        val isPipe = PipedInputStream(osPipe)
        DataBufferUtils.write(dataBuffer, osPipe)
            .subscribeOn(Schedulers.boundedElastic())
            .doOnComplete {
                try {
                    osPipe.close()
                } catch (ignored: IOException) {
                    logger.error { "DataBuffer to InputStream Error. OutputStream Close" }
                }
            }
            .doOnError {
                logger.error(it) { "DataBuffer to InputStream Error" }
                throw StarterException(ErrorCode.UNKNOWN, "DataBuffer to InputStream Error", it)
            }
            .subscribe(DataBufferUtils.releaseConsumer())
        return isPipe
    }

    fun toInputStream2(dataBuffer: Flux<DataBuffer>): InputStream {
        val osPipe = PipedOutputStream()
        val isPipe = PipedInputStream(osPipe)
        DataBufferUtils.write(dataBuffer, osPipe)
            .publishOn(Schedulers.boundedElastic())
            .doOnNext { DataBufferUtils.release(it) }
            .doOnComplete {
                try {
                    osPipe.close()
                } catch (ignored: IOException) {
                    logger.error { "DataBuffer to InputStream Error. OutputStream Close" }
                }
            }
            .doOnError {
                logger.error(it) { "DataBuffer to InputStream Error" }
                throw StarterException(ErrorCode.UNKNOWN, "DataBuffer to InputStream Error", it)
            }
            .subscribe()
        return isPipe
    }
}
