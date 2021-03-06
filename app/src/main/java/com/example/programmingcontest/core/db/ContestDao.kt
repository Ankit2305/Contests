package com.example.programmingcontest.core.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.programmingcontest.core.model.Contest

@Dao
interface ContestDao {

    @Insert
    fun insert(contest: Contest)

    @Delete
    fun delete(contest: Contest)

    @Query("DELETE FROM contest_table")
    fun clear()

    @Query("SELECT * FROM contest_table ORDER BY start_time")
    fun getContests(): LiveData<List<Contest>>

    @Query("SELECT * FROM contest_table WHERE start_time - :currentTime < 86400000 AND start_time > :currentTime ORDER BY start_time")
    fun getContestsIn24Hour(currentTime: Long): LiveData<List<Contest>>

    @Query("SELECT * FROM contest_table WHERE site = :site AND start_time > :currentTime ORDER BY start_time")
    fun getContestsOfSite(site: String, currentTime: Long): List<Contest>

    @Query("SELECT * FROM contest_table WHERE name = :name ORDER BY start_time")
    fun searchContest(name: String): List<Contest>

    @Query("SELECT * FROM contest_table ORDER BY start_time")
    fun searchContests(): List<Contest>

    @Query("SELECT * FROM contest_table WHERE duration <= 18000 ORDER BY start_time")
    fun getShortContest(): List<Contest>

    @Query("SELECT * FROM contest_table WHERE duration > 18000 ORDER BY start_time")
    fun getLongContest(): List<Contest>

    @Query("SELECT * FROM contest_table WHERE start_time <= :currentTime AND end_time > :currentTime ORDER BY start_time")
    fun getOngoingContest(currentTime: Long): List<Contest>

    @Query("SELECT * FROM contest_table WHERE start_time > :currentTime ORDER BY start_time")
    fun getUpcomingContest(currentTime: Long): List<Contest>

}