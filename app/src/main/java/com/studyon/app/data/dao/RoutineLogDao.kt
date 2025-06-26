package com.studyon.app.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.studyon.app.data.entity.RoutineLogEntity

@Dao
interface RoutineLogDao {
    @Query("SELECT * FROM routine_logs ORDER BY date DESC")
    fun getAllRoutineLogs(): LiveData<List<RoutineLogEntity>>

    @Query("SELECT * FROM routine_logs WHERE date = :date")
    fun getRoutineLogsByDate(date: String): LiveData<List<RoutineLogEntity>>

    @Query("SELECT * FROM routine_logs WHERE goalId = :goalId ORDER BY date DESC")
    fun getRoutineLogsByGoal(goalId: Int): LiveData<List<RoutineLogEntity>>

    @Query("SELECT SUM(duration) FROM routine_logs WHERE date = :date")
    suspend fun getTotalDurationByDate(date: String): Int?

    @Query("SELECT * FROM routine_logs WHERE date BETWEEN :startDate AND :endDate ORDER BY date DESC")
    fun getRoutineLogsBetweenDates(startDate: String, endDate: String): LiveData<List<RoutineLogEntity>>

    @Insert
    suspend fun insertRoutineLog(routineLog: RoutineLogEntity)

    @Update
    suspend fun updateRoutineLog(routineLog: RoutineLogEntity)

    @Delete
    suspend fun deleteRoutineLog(routineLog: RoutineLogEntity)

    @Query("DELETE FROM routine_logs")
    suspend fun deleteAllRoutineLogs()
} 