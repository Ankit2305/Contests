package com.example.programmingcontest.core.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.programmingcontest.core.model.Site

@Dao
interface SiteDao {

    @Insert
    fun insert(site: Site)

    @Delete
    fun delete(site: Site)

    @Update
    fun update(site: Site)

    @Query("DELETE FROM site_table")
    fun clear()

    @Query("SELECT * FROM site_table")
    fun getSites(): LiveData<List<Site>>

    @Query("SELECT * FROM site_table WHERE name = :name")
    fun searchSite(name: String): List<Site>
}