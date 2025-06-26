package com.studyon.app.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.studyon.app.data.database.StudyOnDatabase
import com.studyon.app.data.entity.RoutineLogEntity
import com.studyon.app.data.entity.TodoEntity
import com.studyon.app.data.preferences.AppSettings
import com.studyon.app.data.repository.StudyOnRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var repository: StudyOnRepository
    private lateinit var appSettings: AppSettings
    
    private val _todayStudyTime = MutableLiveData<Int>()
    val todayStudyTime: LiveData<Int> = _todayStudyTime
    
    private val _greeting = MutableLiveData<String>()
    val greeting: LiveData<String> = _greeting
    
    private val _currentDate = MutableLiveData<String>()
    val currentDate: LiveData<String> = _currentDate

    val allTodos: LiveData<List<TodoEntity>> by lazy {
        if (::repository.isInitialized) {
            repository.getAllTodos()
        } else {
            MutableLiveData(emptyList())
        }
    }

    init {
        try {
            val database = StudyOnDatabase.getDatabase(application)
            repository = StudyOnRepository(
                database.goalDao(),
                database.todoDao(),
                database.routineLogDao(),
                database.awardDao()
            )
            appSettings = AppSettings(application)
            
            updateCurrentDate()
            updateGreeting()
            
            // 루틴 로그 데이터가 변경될 때마다 오늘의 공부 시간 업데이트
            repository.getAllRoutineLogs().observeForever { logs ->
                logs?.let { 
                    updateTodayStudyTime(it)
                }
            }
        } catch (e: Exception) {
            android.util.Log.e("HomeViewModel", "Error in init", e)
        }
    }

    private fun updateCurrentDate() {
        val formatter = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
        val today = formatter.format(Date())
        _currentDate.value = today
    }

    private fun updateGreeting() {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val greeting = when (hour) {
            in 6..11 -> "좋은 아침이에요! ☀️"
            in 12..17 -> "좋은 오후에요! 🌤️"
            in 18..21 -> "좋은 저녁이에요! 🌆"
            else -> "늦은 시간까지 고생이 많아요! 🌙"
        }
        _greeting.value = greeting
    }

    private fun updateTodayStudyTime(logs: List<RoutineLogEntity>) {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val today = formatter.format(Date())
        val totalTime = logs.filter { it.date == today }.sumOf { it.duration }
        _todayStudyTime.value = totalTime
    }

    fun toggleTodoStatus(todoId: Int, isDone: Boolean) {
        viewModelScope.launch {
            repository.updateTodoStatus(todoId, isDone)
        }
    }

    fun getTodayStudyTimeString(): String {
        val minutes = todayStudyTime.value ?: 0
        val hours = minutes / 60
        val remainingMinutes = minutes % 60
        return when {
            hours > 0 -> "${hours}시간 ${remainingMinutes}분"
            remainingMinutes > 0 -> "${remainingMinutes}분"
            else -> "아직 기록이 없어요"
        }
    }
} 