package com.studyon.app.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0014\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000f0\u000eH\'J\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00072\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\b\u00a8\u0006\u0014"}, d2 = {"Lcom/studyon/app/data/dao/GoalDao;", "", "deleteAllGoals", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteGoal", "goal", "Lcom/studyon/app/data/entity/GoalEntity;", "(Lcom/studyon/app/data/entity/GoalEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteGoalById", "goalId", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllGoals", "Landroidx/lifecycle/LiveData;", "", "getGoalById", "insertGoal", "", "updateGoal", "app_release"})
@androidx.room.Dao()
public abstract interface GoalDao {
    
    @androidx.room.Query(value = "SELECT * FROM goals ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.studyon.app.data.entity.GoalEntity>> getAllGoals();
    
    @androidx.room.Query(value = "SELECT * FROM goals WHERE id = :goalId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getGoalById(int goalId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.studyon.app.data.entity.GoalEntity> $completion);
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertGoal(@org.jetbrains.annotations.NotNull()
    com.studyon.app.data.entity.GoalEntity goal, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateGoal(@org.jetbrains.annotations.NotNull()
    com.studyon.app.data.entity.GoalEntity goal, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteGoal(@org.jetbrains.annotations.NotNull()
    com.studyon.app.data.entity.GoalEntity goal, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM goals WHERE id = :goalId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteGoalById(int goalId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM goals")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAllGoals(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}