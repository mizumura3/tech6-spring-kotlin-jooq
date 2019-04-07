package com.example.springbootkotlinjooq.service

import com.example.springbootkotlinjooq.model.Artist
import com.example.springbootkotlinjooq.model.Music
import com.example.springbootkotlinjooq.repository.ArtistRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ArtistService(
        private val artistRepository: ArtistRepository
) {

    @Transactional
    fun create(artist: Artist) {
        val m = listOf<Music>(
                Music(id = 999,
                        title = "Sun Sebastian",
                        artistId = 999
                )
        )
        artistRepository.create(artist.copy(musics = m))
    }
}