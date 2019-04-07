package com.example.springbootkotlinjooq.repository

interface MusicRepository {

    /**
     * artist_id と一致するレコード数を返却する
     */
    fun groupByArtistId(artistId: Int): Int
}