package com.deliveryhero.clone.user_service

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("/")
    fun home(): String {
        return "Hello, food delivery world!"
    }
}
