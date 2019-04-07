package com.example.springbootkotlinjooq.repository.impl

import com.example.springbootkotlinjooq.model.Artist
import com.example.springbootkotlinjooq.repository.ArtistRepository
import io.mockk.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * MockK のサンプルです。
 */
class ArtistRepositoryMock {

    @Test
    fun mockTest() {
        val r = mockk<ArtistRepository>()
        every { r.findAll() } returns listOf()

        val result = r.findAll()
        verify { r.findAll() }

        confirmVerified(r)
        assertThat(result.isEmpty()).isTrue()
    }

    @Test
    fun mockTest_arg() {
        val r = mockk<ArtistRepository>()
        every { r.findArtistByNativeQuery(any()) } returns Artist(id = 1, name = "Martin Garrix")

        val result = r.findArtistByNativeQuery(2)
        verify { r.findArtistByNativeQuery(any()) }

        confirmVerified(r)
        assertThat(result.id).isEqualTo(1)
        assertThat(result.name).isEqualTo("Martin Garrix")
    }

    @Test
    fun mockTest_relaxed() {
        val r = mockk<ArtistRepository>(relaxed = true)

        val findAllResult = r.findAll()
        val nativeResult = r.findArtistByNativeQuery(1)

        verify {
            r.findAll()
            r.findArtistByNativeQuery(1)
        }

        confirmVerified(r)

        // findAll の実行結果
        assertThat(findAllResult.size).isEqualTo(0)

        // findArtistByNativeQuery の実行結果
        // name は not null だが null が格納されている
        assertThat(nativeResult.id).isEqualTo(0)
        assertThat(nativeResult.name).isEqualTo("")
        assertThat(nativeResult.musics.size).isEqualTo(0)
    }

    @Test
    fun mockTest_capture() {
        val slot = slot<Artist>()
        val r = mockk<ArtistRepository>()

        every {
            r.update(capture(slot))
        } answers {
            Unit
        }

        r.update(Artist(id = 1, name = "name"))

        verify { r.update(slot.captured) }
        confirmVerified(r)
    }
}