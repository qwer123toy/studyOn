package com.studyon.app.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b0\nH\'J$\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b0\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\'J\u001c\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b0\n2\u0006\u0010\u0011\u001a\u00020\u000eH\'J\u001c\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b0\n2\u0006\u0010\u0013\u001a\u00020\u0014H\'J\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0011\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u0016J\u0016\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\b\u00a8\u0006\u0019"}, d2 = {"Lcom/studyon/app/data/dao/RoutineLogDao;", "", "deleteAllRoutineLogs", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteRoutineLog", "routineLog", "Lcom/studyon/app/data/entity/RoutineLogEntity;", "(Lcom/studyon/app/data/entity/RoutineLogEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllRoutineLogs", "Landroidx/lifecycle/LiveData;", "", "getRoutineLogsBetweenDates", "startDate", "", "endDate", "getRoutineLogsByDate", "date", "getRoutineLogsByGoal", "goalId", "", "getTotalDurationByDate", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertRoutineLog", "updateRoutineLog", "app_debug"})
@androidx.room.Dao()
public abstract interface RoutineLogDao {
    
    @androidx.room.Query(value = "SELECT * FROM routine_logs ORDER BY date DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.studyon.app.data.entity.RoutineLogEntity>> getAllRoutineLogs();
    
    @androidx.room.Query(value = "SELECT * FROM routine_logs WHERE date = :date")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.studyon.app.data.entity.RoutineLogEntity>> getRoutineLogsByDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date);
    
    @androidx.room.Query(value = "SELECT * FROM routine_logs WHERE goalId = :goalId ORDER BY date DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.studyon.app.data.entity.RoutineLogEntity>> getRoutineLogsByGoal(int goalId);
    
    @androidx.room.Query(value = "SELECT SUM(duration) FROM routine_logs WHERE date = :date")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTotalDurationByDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM routine_logs WHERE date BETWEEN :startDate AND :endDate ORDER BY date DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.studyon.app.data.entity.RoutineLogEntity>> getRoutineLogsBetweenDates(@org.jetbrains.annotations.NotNull()
    java.lang.String startDate, @org.jetbrains.annotations.NotNull()
    java.lang.String endDate);
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertRoutineLog(@org.jetbrains.annotations.NotNull()
    com.studyon.app.data.entity.RoutineLogEntity routineLog, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateRoutineLog(@org.jetbrains.annotations.NotNull()
    com.studyon.app.data.entity.RoutineLogEntity routineLog, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteRoutineLog(@org.jetbrains.annotations.NotNull()
    com.studyon.app.data.entity.RoutineLogEntity routineLog, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM routine_logs")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAllRoutineLogs(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}