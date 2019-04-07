package com.example.springbootkotlinjooq.model

data class Artist(
        val id: Int,
        val name: String,
        val musics: List<Music> = listOf()
)