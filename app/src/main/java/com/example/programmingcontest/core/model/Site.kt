package com.example.programmingcontest.core.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "site_table")
data class Site(
    @PrimaryKey
    val name: String,

    val url: String
) {
    companion object {
        fun parseSite(item: List<String>): Site? {
            if(item.size == 3)
                return Site(item.get(0), item.get(2))
            return null
        }
    }
}