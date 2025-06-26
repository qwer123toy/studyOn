package com.studyon.app.data.database;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&\u00a8\u0006\f"}, d2 = {"Lcom/studyon/app/data/database/StudyOnDatabase;", "Landroidx/room/RoomDatabase;", "()V", "awardDao", "Lcom/studyon/app/data/dao/AwardDao;", "goalDao", "Lcom/studyon/app/data/dao/GoalDao;", "routineLogDao", "Lcom/studyon/app/data/dao/RoutineLogDao;", "todoDao", "Lcom/studyon/app/data/dao/TodoDao;", "Companion", "app_release"})
@androidx.room.Database(entities = {com.studyon.app.data.entity.GoalEntity.class, com.studyon.app.data.entity.TodoEntity.class, com.studyon.app.data.entity.RoutineLogEntity.class, com.studyon.app.data.entity.AwardEntity.class}, version = 2, exportSchema = false)
public abstract class StudyOnDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.studyon.app.data.database.StudyOnDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.studyon.app.data.database.StudyOnDatabase.Companion Companion = null;
    
    public StudyOnDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.studyon.app.data.dao.GoalDao goalDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.studyon.app.data.dao.TodoDao todoDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.studyon.app.data.dao.RoutineLogDao routineLogDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.studyon.app.data.dao.AwardDao awardDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/studyon/app/data/database/StudyOnDatabase$Companion;", "", "()V", "INSTANCE", "Lcom/studyon/app/data/database/StudyOnDatabase;", "getDatabase", "context", "Landroid/content/Context;", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.studyon.app.data.database.StudyOnDatabase getDatabase(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}