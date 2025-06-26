package com.studyon.app.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.studyon.app.data.entity.AwardEntity

@Dao
interface AwardDao {
    @Query("SELECT * FROM awards ORDER BY earnedAt DESC")
    fun getAllAwards(): LiveData<List<AwardEntity>>

    @Query("SELECT * FROM awards WHERE type = :type")
    fun getAwardsByType(type: String): LiveData<List<AwardEntity>>

    @Insert
    suspend fun insertAward(award: AwardEntity)

    @Update
    suspend fun updateAward(award: AwardEntity)

    @Delete
    suspend fun deleteAward(award: AwardEntity)

    @Query("SELECT COUNT(*) FROM awards WHERE type = :type")
    suspend fun getAwardCountByType(type: String): Int

    @Query("DELETE FROM awards")
    suspend fun deleteAllAwards()
} 