package com.starter.user.app

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeRestController {

    @GetMapping("/api/home")
    fun getHome(): HomeResult {
        return HomeResult("home")
    }
}

data class HomeResult(val message: String)
