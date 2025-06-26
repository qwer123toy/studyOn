package com.studyon.app.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.studyon.app.data.database.StudyOnDatabase
import com.studyon.app.data.entity.RoutineLogEntity
import com.studyon.app.data.repository.StudyOnRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class StatisticsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: StudyOnRepository

    private val _weeklyStats = MutableLiveData<List<Pair<String, Int>>>()
    val weeklyStats: LiveData<List<Pair<String, Int>>> = _weeklyStats

    private val _monthlyStats = MutableLiveData<List<Pair<String, Int>>>()
    val monthlyStats: LiveData<List<Pair<String, Int>>> = _monthlyStats

    private val _totalStudyTime = MutableLiveData<Int>()
    val totalStudyTime: LiveData<Int> = _totalStudyTime

    private val _averageStudyTime = MutableLiveData<Int>()
    val averageStudyTime: LiveData<Int> = _averageStudyTime

    private val _studyStreak = MutableLiveData<Int>()
    val studyStreak: LiveData<Int> = _studyStreak

    private val _selectedPeriod = MutableLiveData<String>()
    val selectedPeriod: LiveData<String> = _selectedPeriod

    private val allRoutineLogs: LiveData<List<RoutineLogEntity>>

    init {
        val database = StudyOnDatabase.getDatabase(application)
        repository = StudyOnRepository(
            database.goalDao(),
            database.todoDao(),
            database.routineLogDao(),
            database.awardDao()
        )
        
        allRoutineLogs = repository.getAllRoutineLogs()
        _selectedPeriod.value = "weekly"
        
        // 루틴 로그 데이터가 변경될 때마다 통계 다시 계산
        allRoutineLogs.observeForever { logs ->
            logs?.let { 
                loadStatistics(it)
            }
        }
    }

    fun setPeriod(period: String) {
        _selectedPeriod.value = period
        allRoutineLogs.value?.let { logs ->
            loadStatistics(logs)
        }
    }

    private fun loadStatistics(logs: List<RoutineLogEntity>) {
        viewModelScope.launch {
            when (selectedPeriod.value) {
                "weekly" -> loadWeeklyStatistics(logs)
                "monthly" -> loadMonthlyStatistics(logs)
            }
            calculateTotalStats(logs)
        }
    }

    private fun loadWeeklyStatistics(logs: List<RoutineLogEntity>) {
        val weeklyData = mutableListOf<Pair<String, Int>>()
        val dateFormatter = SimpleDateFormat("MM/dd", Locale.getDefault())
        val dbDateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        
        for (i in 6 downTo 0) {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_YEAR, -i)
            val dateString = dbDateFormatter.format(calendar.time)
            val displayDate = dateFormatter.format(calendar.time)
            
            val dayTotal = logs.filter { it.date == dateString }.sumOf { it.duration }
            weeklyData.add(Pair(displayDate, dayTotal))
        }
        
        _weeklyStats.value = weeklyData
    }

    private fun loadMonthlyStatistics(logs: List<RoutineLogEntity>) {
        val monthlyData = mutableListOf<Pair<String, Int>>()
        val dateFormatter = SimpleDateFormat("MM/dd", Locale.getDefault())
        val dbDateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        
        for (i in 29 downTo 0) {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_YEAR, -i)
            val dateString = dbDateFormatter.format(calendar.time)
            val displayDate = dateFormatter.format(calendar.time)
            
            val dayTotal = logs.filter { it.date == dateString }.sumOf { it.duration }
            monthlyData.add(Pair(displayDate, dayTotal))
        }
        
        _monthlyStats.value = monthlyData
    }

    private fun calculateTotalStats(logs: List<RoutineLogEntity>) {
        val total = logs.sumOf { it.duration }
        _totalStudyTime.value = total

        val uniqueDates = logs.map { it.date }.distinct()
        val average = if (uniqueDates.isNotEmpty()) total / uniqueDates.size else 0
        _averageStudyTime.value = average

        calculateStudyStreak(logs)
    }

    private fun calculateStudyStreak(logs: List<RoutineLogEntity>) {
        val studyDates = logs.groupBy { it.date }
            .filter { (_, dayLogs) -> dayLogs.sumOf { it.duration } > 0 }
            .keys
            .sorted()
            .reversed()
        
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val calendar = Calendar.getInstance()
        
        var streak = 0
        var currentDate = formatter.format(calendar.time)
        
        while (studyDates.contains(currentDate)) {
            streak++
            calendar.add(Calendar.DAY_OF_YEAR, -1)
            currentDate = formatter.format(calendar.time)
        }
        
        _studyStreak.value = streak
    }

    fun formatDuration(minutes: Int): String {
        val hours = minutes / 60
        val remainingMinutes = minutes % 60
        return when {
            hours > 0 -> "${hours}시간 ${remainingMinutes}분"
            remainingMinutes > 0 -> "${remainingMinutes}분"
            else -> "0분"
        }
    }

    fun getAchievementRate(): Float {
        val total = totalStudyTime.value ?: 0
        val target = 1800 // 30시간 목표 (분 단위)
        return if (target > 0) (total.toFloat() / target) * 100 else 0f
    }

    override fun onCleared() {
        super.onCleared()
        allRoutineLogs.removeObserver { }
    }
} 