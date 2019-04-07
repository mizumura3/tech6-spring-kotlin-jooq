package com.example.springbootkotlinjooq.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController // ①
class HelloWorldController {

    @GetMapping("/hello") // ②
    fun hello(): String {
        return "Hello World" // ③
    }
}