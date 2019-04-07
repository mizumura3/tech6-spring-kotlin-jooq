package com.example.springbootkotlinjooq.repository

import com.example.springbootkotlinjooq.model.Artist

/**
 * Artist を操作するリポジトリのインタフェース
 */
interface ArtistRepository {

    /**
     * 全件取得
     */
    fun findAll(): List<Artist>

    /**
     * 名前の降順でソートする
     */
    fun findAllOrderByName(): List<Artist>

    /**
     * レコードを作成
     */
    fun create(artist: Artist): Artist

    /**
     * レコードを更新
     */
    fun update(artist: Artist)

    /**
     * Artist と Music を返却する
     */
    fun findArtistAndMusic(): List<Artist>

    /**
     * ネイティブクエリで Artist を取得する
     */
    fun findArtistByNativeQuery(id: Int): Artist

    /**
     * id と一致する Artist が存在する場合は true を返却する
     */
    fun existsById(id: Int):Boolean
}