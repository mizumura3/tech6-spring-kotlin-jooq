package com.example.springbootkotlinjooq.service

import com.example.springbootkotlinjooq.model.Artist
import com.example.springbootkotlinjooq.repository.ArtistRepository
import io.mockk.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class ArtistServiceTest {

    @Test
    fun create() {
        val r = mockk<ArtistRepository>()
        val slot = slot<Artist>() // ①
        val s = ArtistService(r)

        every {
            r.create(capture(slot)) // ②
        } answers {
            slot.captured // ③
        }

        val a = Artist(id = 1, name = "test")
        s.create(a)

        verify { r.create(slot.captured) } // ④
        confirmVerified(r)

        val result = slot.captured

        // ⑤
        assertThat(result.musics[0].id).isEqualTo(999)
        assertThat(result.musics[0].title).isEqualTo("Sun Sebastian")
        assertThat(result.musics[0].artistId).isEqualTo(999)
    }
}