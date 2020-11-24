package com.example.programmingcontest.core.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "contest_table")
data class Contest(
    @PrimaryKey
    val name: String,

    val url: String,

    @ColumnInfo(name = "start_time")
    val start_time: Long,

    @ColumnInfo(name = "end_time")
    val end_time: Long,

    val duration: Long,

    val site: String,

    @ColumnInfo(name = "in_24_hours")
    val in_24_hours: Boolean,

    val ongoing: Boolean
): Serializable