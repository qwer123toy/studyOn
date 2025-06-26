package com.studyon.app.data.preferences

import android.content.Context
import android.content.SharedPreferences

class AppSettings(context: Context) {
    private val sharedPreferences: SharedPreferences = 
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "studyon_preferences"
        private const val KEY_LAST_OPEN_DATE = "last_open_date"
        private const val KEY_DAILY_ROUTINE_CHECKED = "daily_routine_checked"
        private const val KEY_ENABLE_NOTIFICATIONS = "enable_notifications"
        private const val KEY_THEME_MODE = "theme_mode"
        private const val KEY_FIRST_RUN = "first_run"
        private const val KEY_LAST_BACKUP_DATE = "last_backup_date"
    }

    var lastOpenDate: String
        get() = sharedPreferences.getString(KEY_LAST_OPEN_DATE, "") ?: ""
        set(value) = sharedPreferences.edit().putString(KEY_LAST_OPEN_DATE, value).apply()

    var dailyRoutineChecked: Boolean
        get() = sharedPreferences.getBoolean(KEY_DAILY_ROUTINE_CHECKED, false)
        set(value) = sharedPreferences.edit().putBoolean(KEY_DAILY_ROUTINE_CHECKED, value).apply()

    var enableNotifications: Boolean
        get() = sharedPreferences.getBoolean(KEY_ENABLE_NOTIFICATIONS, true)
        set(value) = sharedPreferences.edit().putBoolean(KEY_ENABLE_NOTIFICATIONS, value).apply()

    var themeMode: String
        get() = sharedPreferences.getString(KEY_THEME_MODE, "light") ?: "light"
        set(value) = sharedPreferences.edit().putString(KEY_THEME_MODE, value).apply()

    var isFirstRun: Boolean
        get() = sharedPreferences.getBoolean(KEY_FIRST_RUN, true)
        set(value) = sharedPreferences.edit().putBoolean(KEY_FIRST_RUN, value).apply()

    var lastBackupDate: String
        get() = sharedPreferences.getString(KEY_LAST_BACKUP_DATE, "") ?: ""
        set(value) = sharedPreferences.edit().putString(KEY_LAST_BACKUP_DATE, value).apply()

    fun clearAllData() {
        sharedPreferences.edit().clear().apply()
    }
} 