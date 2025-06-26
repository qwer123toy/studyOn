package com.studyon.app.ui.viewmodel

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.studyon.app.data.database.StudyOnDatabase
import com.studyon.app.data.preferences.AppSettings
import com.studyon.app.data.repository.StudyOnRepository
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: StudyOnRepository
    private val appSettings: AppSettings
    private val handler = Handler(Looper.getMainLooper())

    private val _enableNotifications = MutableLiveData<Boolean>()
    val enableNotifications: LiveData<Boolean> = _enableNotifications
    val notificationEnabled: LiveData<Boolean> = _enableNotifications

    private val _themeMode = MutableLiveData<String>()
    val themeMode: LiveData<String> = _themeMode
    val selectedTheme: LiveData<String> = _themeMode

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _lastBackupDate = MutableLiveData<String>()
    val lastBackupDate: LiveData<String> = _lastBackupDate

    init {
        val database = StudyOnDatabase.getDatabase(application)
        repository = StudyOnRepository(
            database.goalDao(),
            database.todoDao(),
            database.routineLogDao(),
            database.awardDao()
        )
        appSettings = AppSettings(application)
        
        loadSettings()
        _message.value = "" // 초기 메시지는 빈 값으로 설정
    }

    private fun loadSettings() {
        _enableNotifications.value = appSettings.enableNotifications
        _themeMode.value = appSettings.themeMode
        _lastBackupDate.value = appSettings.lastBackupDate
    }

    fun setNotificationEnabled(enabled: Boolean) {
        if (appSettings.enableNotifications != enabled) { // 값이 실제로 변경된 경우만
            appSettings.enableNotifications = enabled
            _enableNotifications.value = enabled
            _message.value = if (enabled) "알림이 활성화되었습니다" else "알림이 비활성화되었습니다"
            clearMessageAfterDelay()
        }
    }

    fun setThemeMode(theme: String) {
        if (appSettings.themeMode != theme) { // 값이 실제로 변경된 경우만
            appSettings.themeMode = theme
            _themeMode.value = theme
            
            // 테마 적용
            when (theme) {
                "light" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                "dark" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            
            _message.value = when (theme) {
                "light" -> "밝은 테마로 변경되었습니다"
                "dark" -> "어두운 테마로 변경되었습니다"
                else -> "테마가 변경되었습니다"
            }
            clearMessageAfterDelay()
        }
    }

    fun setTheme(theme: String) {
        setThemeMode(theme)
    }

    fun resetAllData() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // 모든 테이블 데이터 삭제
                val database = StudyOnDatabase.getDatabase(getApplication())
                
                // 각 DAO를 통해 데이터 삭제
                val goalDao = database.goalDao()
                val todoDao = database.todoDao()
                val routineLogDao = database.routineLogDao()
                val awardDao = database.awardDao()
                
                // 모든 데이터 삭제
                goalDao.deleteAllGoals()
                todoDao.deleteAllTodos()
                routineLogDao.deleteAllRoutineLogs()
                awardDao.deleteAllAwards()
                
                // SharedPreferences 일부 초기화 (테마, 알림 설정은 유지)
                appSettings.lastBackupDate = ""
                
                // 설정값 다시 로드
                loadSettings()
                
                _message.value = "모든 데이터가 초기화되었습니다"
                clearMessageAfterDelay()
            } catch (e: Exception) {
                _message.value = "데이터 초기화에 실패했습니다: ${e.message}"
                clearMessageAfterDelay()
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun clearMessageAfterDelay() {
        handler.postDelayed({
            _message.value = ""
        }, 2000)
    }

    fun getAppVersion(): String {
        return "1.0.0"
    }

    fun getAppInfo(): Map<String, String> {
        return mapOf(
            "앱 이름" to "StudyOn",
            "버전" to getAppVersion(),
            "개발자" to "StudyOn Team",
            "문의" to "contact@studyon.com"
        )
    }

    fun exportData() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                // TODO: 데이터 내보내기 구현
                _message.value = "데이터 내보내기 기능은 곧 제공될 예정입니다"
                clearMessageAfterDelay()
            } catch (e: Exception) {
                _message.value = "데이터 내보내기에 실패했습니다: ${e.message}"
                clearMessageAfterDelay()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun importData() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                // TODO: 데이터 가져오기 구현
                _message.value = "데이터 가져오기 기능은 곧 제공될 예정입니다"
                clearMessageAfterDelay()
            } catch (e: Exception) {
                _message.value = "데이터 가져오기에 실패했습니다: ${e.message}"
                clearMessageAfterDelay()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getTotalStats(): Map<String, String> {
        // TODO: 실제 통계 계산
        return mapOf(
            "총 목표 수" to "0개",
            "완료한 할일" to "0개",
            "총 공부 시간" to "0분",
            "획득한 배지" to "0개"
        )
    }

    override fun onCleared() {
        super.onCleared()
        handler.removeCallbacksAndMessages(null)
    }
} 