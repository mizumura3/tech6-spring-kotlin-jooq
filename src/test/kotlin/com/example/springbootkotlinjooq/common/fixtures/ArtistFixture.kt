package com.example.sprigbootkotlinjooq.common.fixtures

import com.ninja_squad.dbsetup_kotlin.DbSetupBuilder
import com.ninja_squad.dbsetup_kotlin.mappedValues

data class ArtistFixture (
    val id: Int = 0, // id
    val name: String = "" // 名前
)

fun DbSetupBuilder.insertArtistFixture(f: ArtistFixture) {
    insertInto("artist") {
        mappedValues(
                "id" to f.id,
                "name" to f.name
        )
    }
}