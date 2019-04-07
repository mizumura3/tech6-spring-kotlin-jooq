package com.example.springbootkotlinjooq.repository.impl

import com.example.sprigbootkotlinjooq.common.fixtures.MusicFixture
import com.example.sprigbootkotlinjooq.common.fixtures.insertMusicFixture
import com.example.springbootkotlinjooq.repository.MusicRepository
import com.ninja_squad.dbsetup_kotlin.dbSetup
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.sql.DataSource

@SpringBootTest
internal class MusicRepositoryImplTest {

    @Autowired lateinit var repository: MusicRepository
    @Autowired lateinit var dataSource: DataSource

    @BeforeEach
    fun before() {
        dbSetup(to = dataSource) {
            deleteAllFrom("music")
            insertMusicFixture(MusicFixture(
                    id = 1,
                    artist_id = 1,
                    title = "aaa1"))
            insertMusicFixture(MusicFixture(
                    id = 2,
                    artist_id = 1,
                    title = "aaa2"))
            insertMusicFixture(MusicFixture(
                    id = 3,
                    artist_id = 1,
                    title = "aaa3"))
            insertMusicFixture(MusicFixture(
                    id = 4,
                    artist_id = 1,
                    title = "aaa4"))
            insertMusicFixture(MusicFixture(
                    id = 5,
                    artist_id = 2,
                    title = "bbb1"))
            insertMusicFixture(MusicFixture(
                    id = 6,
                    artist_id = 2,
                    title = "bbb2"))
            insertMusicFixture(MusicFixture(
                    id = 8,
                    artist_id = 3,
                    title = "ccc1"))
            insertMusicFixture(MusicFixture(
                    id = 9,
                    artist_id = 3,
                    title = "ccc2"))
            insertMusicFixture(MusicFixture(
                    id = 10,
                    artist_id = 3,
                    title = "ccc3"))
        }.launch()
    }

    @Test
    fun groupByArtistId() {
        var result = repository.groupByArtistId(1)
        assertThat(result).isEqualTo(4)

        result = repository.groupByArtistId(2)
        assertThat(result).isEqualTo(2)

        result = repository.groupByArtistId(3)
        assertThat(result).isEqualTo(3)

        // 存在しない ID を指定
        result = repository.groupByArtistId(4)
        assertThat(result).isEqualTo(0)
    }
}