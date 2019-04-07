package com.example.springbootkotlinjooq.repository.impl

import com.example.springbootkotlinjooq.repository.MusicRepository
import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.springframework.stereotype.Repository
import spring.kotlin.jooq.generated.jooq.Tables.MUSIC

@Repository
class MusicRepositoryImpl(private val context: DSLContext) : MusicRepository {
    override fun groupByArtistId(artistId: Int): Int {
        return context.select(DSL.count(MUSIC.ARTIST_ID))
                .from(MUSIC)
                .where(MUSIC.ARTIST_ID.eq((artistId)))
                .groupBy(MUSIC.ARTIST_ID)
                .fetchOne(0, Int::class.java)
    }
}