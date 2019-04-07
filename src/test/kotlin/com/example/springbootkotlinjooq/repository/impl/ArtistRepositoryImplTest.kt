package com.example.springbootkotlinjooq.repository.impl

import com.example.sprigbootkotlinjooq.common.fixtures.ArtistFixture
import com.example.sprigbootkotlinjooq.common.fixtures.insertArtistFixture
import com.example.springbootkotlinjooq.model.Artist
import com.example.springbootkotlinjooq.repository.ArtistRepository
import com.ninja_squad.dbsetup_kotlin.dbSetup
import org.assertj.core.api.Assertions.assertThat
import org.assertj.db.api.Assertions
import org.assertj.db.type.Request
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.sql.DataSource

@SpringBootTest
internal class ArtistRepositoryImplTest {

    @Autowired
    lateinit var repository: ArtistRepository

    @Autowired
    lateinit var dataSource: DataSource

    @BeforeEach
    fun beforeEach() {
        dbSetup(to = dataSource) {
            deleteAllFrom("artist")
            insertArtistFixture(ArtistFixture(id = 1, name = "スティーブ・アオキ"))
            insertArtistFixture(ArtistFixture(id = 2, name = "Skrillex"))
            insertArtistFixture(ArtistFixture(id = 3, name = "Avicii"))
        }.launch()
    }

    @Test
    fun findAll() {
        val result = repository.findAll()

        assertThat(result.size).isEqualTo(3)
        assertThat(result[0].name).isEqualTo("スティーブ・アオキ")
        assertThat(result[1].name).isEqualTo("Skrillex")
        assertThat(result[2].name).isEqualTo("Avicii")
    }

    @Test
    fun create() {
        val a = Artist(id = 1, name = "Afrojack")
        val result = repository.create(a)

        assertThat(result.name).isEqualTo("Afrojack")
    }

    @Test
    fun update() {
        val a = Artist(id = 1, name = "ZEDD")
        repository.update(a)

        val req = Request(dataSource, "select * from artist where id = 1")
        Assertions.assertThat(req).column("name").value().isEqualTo("ZEDD")
    }

    @Test
    fun findArtistAndMusic() {
        val result = repository.findArtistAndMusic()

        assertThat(result.size).isEqualTo(3)

        var a = result[0]
        var m = a.musics[0]

        // artist 一人目
        assertThat(a.id).isEqualTo(1)
        assertThat(a.name).isEqualTo("スティーブ・アオキ")
        assertThat(a.musics.size).isEqualTo(2)

        assertThat(m.id).isEqualTo(1)
        assertThat(m.artistId).isEqualTo(1)
        assertThat(m.title).isEqualTo("Cake Face")

        m = a.musics[1]
        assertThat(m.id).isEqualTo(2)
        assertThat(m.artistId).isEqualTo(1)
        assertThat(m.title).isEqualTo("Azukita")

        // artist 二人目
        a = result[1]
        assertThat(a.id).isEqualTo(2)
        assertThat(a.name).isEqualTo("Skrillex")
        assertThat(a.musics.size).isEqualTo(2)

        m = a.musics[0]
        assertThat(m.id).isEqualTo(3)
        assertThat(m.artistId).isEqualTo(2)
        assertThat(m.title).isEqualTo("Face My Fears")

        m = a.musics[1]
        assertThat(m.id).isEqualTo(4)
        assertThat(m.artistId).isEqualTo(2)
        assertThat(m.title).isEqualTo("Recess")

        // artist 3人目
        a = result[2]
        assertThat(a.id).isEqualTo(3)
        assertThat(a.name).isEqualTo("Avicii")
        assertThat(a.musics.size).isEqualTo(2)

        m = a.musics[0]
        assertThat(m.id).isEqualTo(5)
        assertThat(m.artistId).isEqualTo(3)
        assertThat(m.title).isEqualTo("Wake Me Up")

        m = a.musics[1]
        assertThat(m.id).isEqualTo(6)
        assertThat(m.artistId).isEqualTo(3)
        assertThat(m.title).isEqualTo("The Nights")
    }

    @Test
    fun findAllOrderByName() {
        val result = repository.findAllOrderByName()
        assertThat(result.size).isEqualTo(3)

        var a = result[0]
        assertThat(a.name).isEqualTo("Avicii")

        a = result[1]
        assertThat(a.name).isEqualTo("Skrillex")

        a = result[2]
        assertThat(a.name).isEqualTo("スティーブ・アオキ")
    }

    /**
     * レコードが存在する id を指定した場合
     */
    @Test
    fun findArtistByNativeQuery_ok() {
        //
        val result = repository.findArtistByNativeQuery(1)

        assertThat(result.id).isEqualTo(1)
        assertThat(result.name).isEqualTo("スティーブ・アオキ")
    }

    /**
     * レコードが存在しない id を指定した場合
     */
    @Test
    fun findArtistByNativeQuery_no_record() {
        // 存在しない ID を指定した場合
        repository.findArtistByNativeQuery(999)
    }

    /**
     * レコードが存在する場合は true 存在しない場合は false を返却することを確認する
     */
    @Test
    fun existsById() {
        var result = repository.existsById(1)
        assertThat(result).isTrue()

        result = repository.existsById(999)
        assertThat(result).isFalse()
    }
}