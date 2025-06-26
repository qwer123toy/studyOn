package com.studyon.app.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u0013J\u0016\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u0016H\u0086@\u00a2\u0006\u0002\u0010\u0017J\u0016\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u001aH\u0086@\u00a2\u0006\u0002\u0010\u001bJ\u0012\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u001e0\u001dJ\u0012\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u001e0\u001dJ\u0012\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u001e0\u001dJ\u0012\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u001e0\u001dJ\u0016\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0086@\u00a2\u0006\u0002\u0010&J\u001a\u0010\'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u001e0\u001d2\u0006\u0010$\u001a\u00020%J\u0018\u0010(\u001a\u0004\u0018\u00010\u00122\u0006\u0010)\u001a\u00020#H\u0086@\u00a2\u0006\u0002\u0010*J\u0012\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u001e0\u001dJ\"\u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u001e0\u001d2\u0006\u0010-\u001a\u00020%2\u0006\u0010.\u001a\u00020%J\u001a\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u001e0\u001d2\u0006\u00100\u001a\u00020%J\u001a\u00101\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u001e0\u001d2\u0006\u0010)\u001a\u00020#J\u001a\u00102\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u001e0\u001d2\u0006\u0010)\u001a\u00020#J\u0016\u00103\u001a\u00020#2\u0006\u00100\u001a\u00020%H\u0086@\u00a2\u0006\u0002\u0010&J\u0016\u00104\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u00105\u001a\u0002062\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u0013J\u0016\u00107\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u0016H\u0086@\u00a2\u0006\u0002\u0010\u0017J\u0016\u00108\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u001aH\u0086@\u00a2\u0006\u0002\u0010\u001bJ\u0016\u00109\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010:\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u0013J\u0016\u0010;\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u0016H\u0086@\u00a2\u0006\u0002\u0010\u0017J\u0016\u0010<\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u001aH\u0086@\u00a2\u0006\u0002\u0010\u001bJ\u001e\u0010=\u001a\u00020\f2\u0006\u0010>\u001a\u00020#2\u0006\u0010?\u001a\u00020@H\u0086@\u00a2\u0006\u0002\u0010AR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006B"}, d2 = {"Lcom/studyon/app/data/repository/StudyOnRepository;", "", "goalDao", "Lcom/studyon/app/data/dao/GoalDao;", "todoDao", "Lcom/studyon/app/data/dao/TodoDao;", "routineLogDao", "Lcom/studyon/app/data/dao/RoutineLogDao;", "awardDao", "Lcom/studyon/app/data/dao/AwardDao;", "(Lcom/studyon/app/data/dao/GoalDao;Lcom/studyon/app/data/dao/TodoDao;Lcom/studyon/app/data/dao/RoutineLogDao;Lcom/studyon/app/data/dao/AwardDao;)V", "deleteAward", "", "award", "Lcom/studyon/app/data/entity/AwardEntity;", "(Lcom/studyon/app/data/entity/AwardEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteGoal", "goal", "Lcom/studyon/app/data/entity/GoalEntity;", "(Lcom/studyon/app/data/entity/GoalEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteRoutineLog", "routineLog", "Lcom/studyon/app/data/entity/RoutineLogEntity;", "(Lcom/studyon/app/data/entity/RoutineLogEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteTodo", "todo", "Lcom/studyon/app/data/entity/TodoEntity;", "(Lcom/studyon/app/data/entity/TodoEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllAwards", "Landroidx/lifecycle/LiveData;", "", "getAllGoals", "getAllRoutineLogs", "getAllTodos", "getAwardCountByType", "", "type", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAwardsByType", "getGoalById", "goalId", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getIncompleteTodos", "getRoutineLogsBetweenDates", "startDate", "endDate", "getRoutineLogsByDate", "date", "getRoutineLogsByGoal", "getTodosByGoal", "getTotalDurationByDate", "insertAward", "insertGoal", "", "insertRoutineLog", "insertTodo", "updateAward", "updateGoal", "updateRoutineLog", "updateTodo", "updateTodoStatus", "todoId", "isDone", "", "(IZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
public final class StudyOnRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.studyon.app.data.dao.GoalDao goalDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.studyon.app.data.dao.TodoDao todoDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.studyon.app.data.dao.RoutineLogDao routineLogDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.studyon.app.data.dao.AwardDao awardDao = null;
    
    public StudyOnRepository(@org.jetbrains.annotations.NotNull()
    com.studyon.app.data.dao.GoalDao goalDao, @org.jetbrains.annotations.NotNull()
    com.studyon.app.data.dao.TodoDao todoDao, @org.jetbrains.annotations.NotNull()
    com.studyon.app.data.dao.RoutineLogDao routineLogDao, @org.jetbrains.annotations.NotNull()
    com.studyon.app.data.dao.AwardDao awardDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.studyon.app.data.entity.GoalEntity>> getAllGoals() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertGoal(@org.jetbrains.annotations.NotNull()
    com.studyon.app.data.entity.GoalEntity goal, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateGoal(@org.jetbrains.annotations.NotNull()
    com.studyon.app.data.entity.GoalEntity goal, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteGoal(@org.jetbrains.annotations.NotNull()
    com.studyon.app.data.entity.GoalEntity goal, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getGoalById(int goalId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.studyon.app.data.entity.GoalEntity> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.studyon.app.data.entity.TodoEntity>> getAllTodos() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.studyon.app.data.entity.TodoEntity>> getTodosByGoal(int goalId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.studyon.app.data.entity.TodoEntity>> getIncompleteTodos() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertTodo(@org.jetbrains.annotations.NotNull()
    com.studyon.app.data.entity.TodoEntity todo, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateTodo(@org.jetbrains.annotations.NotNull()
    com.studyon.app.data.entity.TodoEntity todo, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteTodo(@org.jetbrains.annotations.NotNull()
    com.studyon.app.data.entity.TodoEntity todo, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateTodoStatus(int todoId, boolean isDone, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.studyon.app.data.entity.RoutineLogEntity>> getAllRoutineLogs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.studyon.app.data.entity.RoutineLogEntity>> getRoutineLogsByDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.studyon.app.data.entity.RoutineLogEntity>> getRoutineLogsByGoal(int goalId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getTotalDurationByDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.studyon.app.data.entity.RoutineLogEntity>> getRoutineLogsBetweenDates(@org.jetbrains.annotations.NotNull()
    java.lang.String startDate, @org.jetbrains.annotations.NotNull()
    java.lang.String endDate) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertRoutineLog(@org.jetbrains.annotations.NotNull()
    com.studyon.app.data.entity.RoutineLogEntity routineLog, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateRoutineLog(@org.jetbrains.annotations.NotNull()
    com.studyon.app.data.entity.RoutineLogEntity routineLog, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteRoutineLog(@org.jetbrains.annotations.NotNull()
    com.studyon.app.data.entity.RoutineLogEntity routineLog, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.studyon.app.data.entity.AwardEntity>> getAllAwards() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.studyon.app.data.entity.AwardEntity>> getAwardsByType(@org.jetbrains.annotations.NotNull()
    java.lang.String type) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertAward(@org.jetbrains.annotations.NotNull()
    com.studyon.app.data.entity.AwardEntity award, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateAward(@org.jetbrains.annotations.NotNull()
    com.studyon.app.data.entity.AwardEntity award, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteAward(@org.jetbrains.annotations.NotNull()
    com.studyon.app.data.entity.AwardEntity award, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAwardCountByType(@org.jetbrains.annotations.NotNull()
    java.lang.String type, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
}