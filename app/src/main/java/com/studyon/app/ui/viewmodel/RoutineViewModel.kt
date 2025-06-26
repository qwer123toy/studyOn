package com.studyon.app.ui.viewmodel

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.studyon.app.data.database.StudyOnDatabase
import com.studyon.app.data.entity.GoalEntity
import com.studyon.app.data.entity.RoutineLogEntity
import com.studyon.app.data.repository.StudyOnRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class RoutineViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: StudyOnRepository

    val allGoals: LiveData<List<GoalEntity>>
    val allRoutineLogs: LiveData<List<RoutineLogEntity>>

    private val _selectedDate = MutableLiveData<String>()
    val selectedDate: LiveData<String> = _selectedDate

    private val _selectedGoalId = MutableLiveData<Int>()
    val selectedGoalId: LiveData<Int> = _selectedGoalId

    private val _studyDuration = MutableLiveData<Int>()
    val studyDuration: LiveData<Int> = _studyDuration

    private val _memo = MutableLiveData<String>()
    val memo: LiveData<String> = _memo

    private val _isTimerRunning = MutableLiveData<Boolean>()
    val isTimerRunning: LiveData<Boolean> = _isTimerRunning

    private val _timerSeconds = MutableLiveData<Int>()
    val timerSeconds: LiveData<Int> = _timerSeconds

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private var timerStartTime: Long = 0
    private var totalSeconds: Int = 0
    private val handler = Handler(Looper.getMainLooper())
    private var timerRunnable: Runnable? = null

    init {
        val database = StudyOnDatabase.getDatabase(application)
        repository = StudyOnRepository(
            database.goalDao(),
            database.todoDao(),
            database.routineLogDao(),
            database.awardDao()
        )
        
        allGoals = repository.getAllGoals()
        allRoutineLogs = repository.getAllRoutineLogs()
        
        // 초기값 설정
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        _selectedDate.value = formatter.format(Date())
        _studyDuration.value = 0
        _memo.value = ""
        _isTimerRunning.value = false
        _timerSeconds.value = 0
        _message.value = ""
    }

    fun setSelectedDate(date: String) {
        _selectedDate.value = date
    }

    fun setSelectedGoalId(goalId: Int) {
        _selectedGoalId.value = goalId
    }

    fun setStudyDuration(minutes: Int) {
        // 직접 입력 시 분과 초를 모두 설정
        totalSeconds = minutes * 60
        _studyDuration.value = minutes
        _timerSeconds.value = totalSeconds
    }

    fun setMemo(memo: String) {
        _memo.value = memo
    }

    fun startTimer() {
        _isTimerRunning.value = true
        timerStartTime = System.currentTimeMillis()
        
        timerRunnable = object : Runnable {
            override fun run() {
                if (_isTimerRunning.value == true) {
                    totalSeconds++
                    _timerSeconds.value = totalSeconds
                    // 분 단위도 실시간 업데이트
                    val minutes = totalSeconds / 60
                    _studyDuration.value = minutes
                    handler.postDelayed(this, 1000)
                }
            }
        }
        handler.post(timerRunnable!!)
    }

    fun stopTimer() {
        _isTimerRunning.value = false
        timerRunnable?.let { handler.removeCallbacks(it) }
        // 정지 시 최종 분 값 업데이트
        val finalMinutes = totalSeconds / 60
        _studyDuration.value = finalMinutes
    }

    fun resetTimer() {
        _isTimerRunning.value = false
        timerRunnable?.let { handler.removeCallbacks(it) }
        totalSeconds = 0
        _timerSeconds.value = 0
        _studyDuration.value = 0
    }

    fun saveRoutineLog() {
        viewModelScope.launch {
            try {
                val goalId = selectedGoalId.value ?: run {
                    _message.value = "목표를 선택해주세요"
                    clearMessageAfterDelay()
                    return@launch
                }
                
                // 저장 시 초 단위 시간을 분 단위로 변환
                val durationInMinutes = if (totalSeconds > 0) {
                    // 최소 1분은 기록되도록 올림 처리
                    kotlin.math.max(1, (totalSeconds + 59) / 60)
                } else {
                    studyDuration.value ?: 0
                }
                
                if (durationInMinutes <= 0) {
                    _message.value = "공부 시간을 입력해주세요"
                    clearMessageAfterDelay()
                    return@launch
                }

                val routineLog = RoutineLogEntity(
                    date = selectedDate.value ?: "",
                    goalId = goalId,
                    duration = durationInMinutes,
                    memo = memo.value ?: ""
                )
                
                repository.insertRoutineLog(routineLog)
                _message.value = "루틴이 기록되었습니다! (${durationInMinutes}분)"
                clearMessageAfterDelay()
                
                // 입력 필드 초기화
                resetInputs()
                
            } catch (e: Exception) {
                _message.value = "루틴 기록에 실패했습니다: ${e.message}"
                clearMessageAfterDelay()
            }
        }
    }

    private fun clearMessageAfterDelay() {
        handler.postDelayed({
            _message.value = ""
        }, 2000)
    }

    private fun resetInputs() {
        _memo.value = ""
        resetTimer()
    }

    fun getRoutineLogsByDate(date: String): LiveData<List<RoutineLogEntity>> {
        return repository.getRoutineLogsByDate(date)
    }

    fun formatDuration(durationMinutes: Int): String {
        val seconds = _timerSeconds.value ?: 0
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        
        return if (minutes > 0) {
            "${minutes}분 ${remainingSeconds}초"
        } else {
            "${remainingSeconds}초"
        }
    }

    override fun onCleared() {
        super.onCleared()
        timerRunnable?.let { handler.removeCallbacks(it) }
    }
} 