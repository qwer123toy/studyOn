package com.studyon.app.data.preferences;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 !2\u00020\u0001:\u0001!B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u001f\u001a\u00020 R$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR$\u0010\f\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000bR$\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u000f\u0010\t\"\u0004\b\u0010\u0010\u000bR$\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00118F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R$\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00118F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R$\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00118F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u001d\u0010\u0014\"\u0004\b\u001e\u0010\u0016\u00a8\u0006\""}, d2 = {"Lcom/studyon/app/data/preferences/AppSettings;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "value", "", "dailyRoutineChecked", "getDailyRoutineChecked", "()Z", "setDailyRoutineChecked", "(Z)V", "enableNotifications", "getEnableNotifications", "setEnableNotifications", "isFirstRun", "setFirstRun", "", "lastBackupDate", "getLastBackupDate", "()Ljava/lang/String;", "setLastBackupDate", "(Ljava/lang/String;)V", "lastOpenDate", "getLastOpenDate", "setLastOpenDate", "sharedPreferences", "Landroid/content/SharedPreferences;", "themeMode", "getThemeMode", "setThemeMode", "clearAllData", "", "Companion", "app_debug"})
public final class AppSettings {
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences sharedPreferences = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PREFS_NAME = "studyon_preferences";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_LAST_OPEN_DATE = "last_open_date";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_DAILY_ROUTINE_CHECKED = "daily_routine_checked";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_ENABLE_NOTIFICATIONS = "enable_notifications";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_THEME_MODE = "theme_mode";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_FIRST_RUN = "first_run";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_LAST_BACKUP_DATE = "last_backup_date";
    @org.jetbrains.annotations.NotNull()
    public static final com.studyon.app.data.preferences.AppSettings.Companion Companion = null;
    
    public AppSettings(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLastOpenDate() {
        return null;
    }
    
    public final void setLastOpenDate(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final boolean getDailyRoutineChecked() {
        return false;
    }
    
    public final void setDailyRoutineChecked(boolean value) {
    }
    
    public final boolean getEnableNotifications() {
        return false;
    }
    
    public final void setEnableNotifications(boolean value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getThemeMode() {
        return null;
    }
    
    public final void setThemeMode(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final boolean isFirstRun() {
        return false;
    }
    
    public final void setFirstRun(boolean value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLastBackupDate() {
        return null;
    }
    
    public final void setLastBackupDate(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void clearAllData() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/studyon/app/data/preferences/AppSettings$Companion;", "", "()V", "KEY_DAILY_ROUTINE_CHECKED", "", "KEY_ENABLE_NOTIFICATIONS", "KEY_FIRST_RUN", "KEY_LAST_BACKUP_DATE", "KEY_LAST_OPEN_DATE", "KEY_THEME_MODE", "PREFS_NAME", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}