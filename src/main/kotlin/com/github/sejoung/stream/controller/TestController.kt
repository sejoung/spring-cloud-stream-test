package com.github.sejoung.stream.controller

import org.apache.logging.log4j.kotlin.Logging
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(
    private val streamBridge: StreamBridge,
) : Logging {

    @GetMapping("/test")
    fun test() {
        runCatching {
            streamBridge.send("disabledAllWalletConnects-out-0", "test")
        }.onSuccess {
            logger.info("success.")
        }.onFailure {
            logger.error("fail.", it)
        }
    }
}
