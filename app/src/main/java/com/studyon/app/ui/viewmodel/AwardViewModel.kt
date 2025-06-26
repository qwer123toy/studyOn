package com.studyon.app.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.studyon.app.data.database.StudyOnDatabase
import com.studyon.app.data.entity.AwardEntity
import com.studyon.app.data.entity.RoutineLogEntity
import com.studyon.app.data.repository.StudyOnRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

data class AwardCondition(
    val type: String,
    val name: String,
    val description: String,
    val targetValue: Int,
    val currentValue: Int,
    val isEarned: Boolean
)

class AwardViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: StudyOnRepository

    val allAwards: LiveData<List<AwardEntity>>

    private val _awardConditions = MutableLiveData<List<AwardCondition>>()
    val awardConditions: LiveData<List<AwardCondition>> = _awardConditions

    private val _studyCalendar = MutableLiveData<Map<String, Boolean>>()
    val studyCalendar: LiveData<Map<String, Boolean>> = _studyCalendar

    private val _currentStreak = MutableLiveData<Int>()
    val currentStreak: LiveData<Int> = _currentStreak

    private val _longestStreak = MutableLiveData<Int>()
    val longestStreak: LiveData<Int> = _longestStreak

    private val _earnedAwardsCount = MutableLiveData<Int>()
    val earnedAwardsCount: LiveData<Int> = _earnedAwardsCount

    init {
        val database = StudyOnDatabase.getDatabase(application)
        repository = StudyOnRepository(
            database.goalDao(),
            database.todoDao(),
            database.routineLogDao(),
            database.awardDao()
        )
        
        allAwards = repository.getAllAwards()
        
        loadAwardConditions()
        loadStudyCalendar()
    }

    private fun loadAwardConditions() {
        viewModelScope.launch {
            // LiveData 대신 직접 데이터를 가져옴
            val logs = repository.getAllRoutineLogs().value ?: emptyList()
            processAwardConditions(logs)
        }
    }
    
    private fun processAwardConditions(logs: List<RoutineLogEntity>) {
        val conditions = mutableListOf<AwardCondition>()
        
        // 연속 공부 일수 관련 배지들
        val currentStreak = calculateCurrentStreak(logs)
        val longestStreak = calculateLongestStreak(logs)
        
        _currentStreak.value = currentStreak
        _longestStreak.value = longestStreak
        
        // 첫 공부 배지
        conditions.add(AwardCondition(
            type = "first_study",
            name = "첫 걸음 🌟",
            description = "첫 공부 기록하기",
            targetValue = 1,
            currentValue = if (logs.isNotEmpty()) 1 else 0,
            isEarned = logs.isNotEmpty()
        ))
        
        // 연속 3일 배지
        conditions.add(AwardCondition(
            type = "streak_3",
            name = "꾸준함의 시작 🔥",
            description = "3일 연속 공부하기",
            targetValue = 3,
            currentValue = currentStreak,
            isEarned = longestStreak >= 3
        ))
        
        // 연속 7일 배지
        conditions.add(AwardCondition(
            type = "streak_7",
            name = "일주일 마스터 ⭐",
            description = "7일 연속 공부하기",
            targetValue = 7,
            currentValue = currentStreak,
            isEarned = longestStreak >= 7
        ))
        
        // 연속 30일 배지
        conditions.add(AwardCondition(
            type = "streak_30",
            name = "한 달 챌린저 🏆",
            description = "30일 연속 공부하기",
            targetValue = 30,
            currentValue = currentStreak,
            isEarned = longestStreak >= 30
        ))
        
        // 총 공부 시간 관련 배지들
        val totalHours = logs.sumOf { it.duration } / 60
        
        // 10시간 배지
        conditions.add(AwardCondition(
            type = "total_10h",
            name = "공부 입문자 📚",
            description = "총 10시간 공부하기",
            targetValue = 10,
            currentValue = totalHours,
            isEarned = totalHours >= 10
        ))
        
        // 50시간 배지
        conditions.add(AwardCondition(
            type = "total_50h",
            name = "공부 중급자 🎓",
            description = "총 50시간 공부하기",
            targetValue = 50,
            currentValue = totalHours,
            isEarned = totalHours >= 50
        ))
        
        // 100시간 배지
        conditions.add(AwardCondition(
            type = "total_100h",
            name = "공부 고수 👑",
            description = "총 100시간 공부하기",
            targetValue = 100,
            currentValue = totalHours,
            isEarned = totalHours >= 100
        ))
        
        _awardConditions.value = conditions
        
        // 새로 달성한 배지 확인 및 저장
        viewModelScope.launch {
            checkAndSaveNewAwards(conditions)
        }
        
        // 획득한 배지 수 업데이트
        _earnedAwardsCount.value = conditions.count { it.isEarned }
    }

    private suspend fun checkAndSaveNewAwards(conditions: List<AwardCondition>) {
        for (condition in conditions) {
            if (condition.isEarned) {
                val existingCount = repository.getAwardCountByType(condition.type)
                if (existingCount == 0) {
                    // 새로운 배지 저장
                    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    val today = formatter.format(Date())
                    
                    val award = AwardEntity(
                        title = condition.name,
                        name = condition.type,
                        description = condition.description,
                        type = condition.type,
                        isEarned = true,
                        earnedAt = today
                    )
                    repository.insertAward(award)
                }
            }
        }
    }

    private fun loadStudyCalendar() {
        viewModelScope.launch {
            // LiveData 대신 직접 데이터를 가져옴
            val logs = repository.getAllRoutineLogs().value ?: emptyList()
            val calendar = mutableMapOf<String, Boolean>()
            val studyDates = logs.map { it.date }.toSet()
            
            // 최근 3개월 데이터 생성
            val cal = Calendar.getInstance()
            for (i in 0..89) { // 90일
                val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val dateString = formatter.format(cal.time)
                calendar[dateString] = studyDates.contains(dateString)
                cal.add(Calendar.DAY_OF_YEAR, -1)
            }
            
            _studyCalendar.value = calendar
        }
    }

    private fun calculateCurrentStreak(logs: List<RoutineLogEntity>): Int {
        val sortedDates = logs.map { it.date }.distinct().sorted().reversed()
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val calendar = Calendar.getInstance()
        
        var streak = 0
        for (i in 0 until 365) { // 최대 1년
            val checkDate = formatter.format(calendar.time)
            if (sortedDates.contains(checkDate)) {
                streak++
                calendar.add(Calendar.DAY_OF_YEAR, -1)
            } else {
                break
            }
        }
        
        return streak
    }

    private fun calculateLongestStreak(logs: List<RoutineLogEntity>): Int {
        val sortedDates = logs.map { it.date }.distinct().sorted()
        if (sortedDates.isEmpty()) return 0
        
        var longestStreak = 1
        var currentStreak = 1
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        
        for (i in 1 until sortedDates.size) {
            val currentDate = formatter.parse(sortedDates[i])
            val previousDate = formatter.parse(sortedDates[i - 1])
            
            val diffInDays = ((currentDate?.time ?: 0) - (previousDate?.time ?: 0)) / (1000 * 60 * 60 * 24)
            
            if (diffInDays == 1L) {
                currentStreak++
                longestStreak = maxOf(longestStreak, currentStreak)
            } else {
                currentStreak = 1
            }
        }
        
        return longestStreak
    }

    fun getProgressPercentage(condition: AwardCondition): Int {
        return if (condition.targetValue > 0) {
            minOf(100, (condition.currentValue * 100) / condition.targetValue)
        } else 0
    }

    fun getNextGoalMessage(): String {
        val conditions = awardConditions.value ?: return ""
        val nextGoal = conditions.firstOrNull { !it.isEarned }
        
        return nextGoal?.let {
            val remaining = it.targetValue - it.currentValue
            when (it.type) {
                "streak_3", "streak_7", "streak_30" -> "${it.name}까지 ${remaining}일 남았어요!"
                else -> "${it.name}까지 ${remaining}시간 남았어요!"
            }
        } ?: "모든 배지를 획득했어요! 🎉"
    }
} 