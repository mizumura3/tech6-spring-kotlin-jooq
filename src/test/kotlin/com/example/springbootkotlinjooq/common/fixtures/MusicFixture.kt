package com.example.sprigbootkotlinjooq.common.fixtures

import com.ninja_squad.dbsetup_kotlin.DbSetupBuilder
import com.ninja_squad.dbsetup_kotlin.mappedValues

data class MusicFixture (
    val id: Int = 0, // id
    val artist_id: Int = 0, // アーティストID
    val title: String = "" // title
)

fun DbSetupBuilder.insertMusicFixture(f: MusicFixture) {
    insertInto("music") {
        mappedValues(
                "id" to f.id,
                "artist_id" to f.artist_id,
                "title" to f.title
        )
    }
}