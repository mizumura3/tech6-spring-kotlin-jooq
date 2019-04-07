package com.example.springbootkotlinjooq.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PostController {

    @PostMapping("post") // ①
    fun post(@RequestBody body: String): ResponseEntity<String> { // ②
        println(body) // ③
        return ResponseEntity.ok(body) // ④
    }
}