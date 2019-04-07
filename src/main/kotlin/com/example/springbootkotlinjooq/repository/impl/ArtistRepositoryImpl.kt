package com.example.springbootkotlinjooq.repository.impl

import com.example.springbootkotlinjooq.model.Artist
import com.example.springbootkotlinjooq.model.Music
import com.example.springbootkotlinjooq.repository.ArtistRepository
import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.springframework.stereotype.Repository
import spring.kotlin.jooq.generated.jooq.Tables.ARTIST
import spring.kotlin.jooq.generated.jooq.Tables.MUSIC
import spring.kotlin.jooq.generated.jooq.tables.records.ArtistRecord

/**
 * Artist を操作するリポジトリの実装
 */
@Repository
class ArtistRepositoryImpl(private val context: DSLContext) : ArtistRepository {
    override fun create(artist: Artist): Artist {
        return context.insertInto(ARTIST)
                .set(ARTIST.NAME, artist.name)
                .returning()
                .fetchOne()
                .let { toModel(it) }
    }

    override fun findAll(): List<Artist> {
        return context.select()
                .from(ARTIST)
                .fetch()
                .map { toModel(it as ArtistRecord) }
    }

    override fun findAllOrderByName(): List<Artist> {
        return context.select()
                .from(ARTIST)
                .orderBy(ARTIST.NAME)
                .fetch()
                .map { toModel(it as ArtistRecord) }
    }

    override fun update(artist: Artist) {
        context.update(ARTIST)
                .set(ARTIST.NAME, artist.name)
                .where(ARTIST.ID.eq(artist.id))
                .execute()
    }

    override fun findArtistAndMusic(): List<Artist> {
        val map = mutableMapOf<Int, MutableList<Music>>()
        return context.select()
                .from(ARTIST)
                .join(MUSIC)
                .on(ARTIST.ID.eq(MUSIC.ARTIST_ID))
                .fetch()
                .map {
                    if (map.containsKey(it.get(MUSIC.ARTIST_ID))) {
                        map.getValue(it.get(MUSIC.ARTIST_ID)).add(Music(id = it.get(MUSIC.ID), artistId = it.get(MUSIC.ARTIST_ID), title = it.get(MUSIC.TITLE)))
                    } else {
                        map[it.get(MUSIC.ARTIST_ID)] = mutableListOf(Music(id = it.get(MUSIC.ID), artistId = it.get(MUSIC.ARTIST_ID), title = it.get(MUSIC.TITLE)))
                    }
                    Artist(id = it.get(ARTIST.ID), name = it.get(ARTIST.NAME), musics = map.getValue(it.get(ARTIST.ID)).toList())
                }.associateBy({ it.id }, { it })
                .values
                .toList()
    }

    override fun findArtistByNativeQuery(id: Int): Artist {
        val result = context.resultQuery("select * from artist where id = :id", DSL.param("id", id)).fetchOne()
        return result?.let { Artist(id = it.get(ARTIST.ID), name = it.get(ARTIST.NAME)) } ?: throw IllegalArgumentException("record not found. id = $id")
    }

    override fun existsById(id: Int): Boolean {
        return context.fetchExists(context.select()
                .from(ARTIST)
                .where(ARTIST.ID.eq(id))
        )
    }

    private fun toModel(r: ArtistRecord): Artist {
        return Artist(r.id, r.name)
    }
}