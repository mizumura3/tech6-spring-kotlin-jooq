/*
 * This file is generated by jOOQ.
 */
package spring.kotlin.jooq.generated.jooq.tables.records;


import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;

import spring.kotlin.jooq.generated.jooq.tables.Music;


/**
 * ミュージック
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MusicRecord extends UpdatableRecordImpl<MusicRecord> implements Record3<Integer, Integer, String> {

    private static final long serialVersionUID = 669033059;

    /**
     * Setter for <code>music.id</code>. id
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>music.id</code>. id
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>music.artist_id</code>. アーティストID
     */
    public void setArtistId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>music.artist_id</code>. アーティストID
     */
    public Integer getArtistId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>music.title</code>. title
     */
    public void setTitle(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>music.title</code>. title
     */
    public String getTitle() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<Integer, Integer, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row3<Integer, Integer, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Music.MUSIC.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field2() {
        return Music.MUSIC.ARTIST_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Music.MUSIC.TITLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component2() {
        return getArtistId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getTitle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value2() {
        return getArtistId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getTitle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MusicRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MusicRecord value2(Integer value) {
        setArtistId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MusicRecord value3(String value) {
        setTitle(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MusicRecord values(Integer value1, Integer value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached MusicRecord
     */
    public MusicRecord() {
        super(Music.MUSIC);
    }

    /**
     * Create a detached, initialised MusicRecord
     */
    public MusicRecord(Integer id, Integer artistId, String title) {
        super(Music.MUSIC);

        set(0, id);
        set(1, artistId);
        set(2, title);
    }
}
