package com.studyon.app.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b0\nH\'J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u0010J\u001c\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b0\n2\u0006\u0010\u000e\u001a\u00020\u000fH\'J\u0016\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\b\u00a8\u0006\u0014"}, d2 = {"Lcom/studyon/app/data/dao/AwardDao;", "", "deleteAllAwards", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAward", "award", "Lcom/studyon/app/data/entity/AwardEntity;", "(Lcom/studyon/app/data/entity/AwardEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllAwards", "Landroidx/lifecycle/LiveData;", "", "getAwardCountByType", "", "type", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAwardsByType", "insertAward", "updateAward", "app_release"})
@androidx.room.Dao()
public abstract interface AwardDao {
    
    @androidx.room.Query(value = "SELECT * FROM awards ORDER BY earnedAt DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.studyon.app.data.entity.AwardEntity>> getAllAwards();
    
    @androidx.room.Query(value = "SELECT * FROM awards WHERE type = :type")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.studyon.app.data.entity.AwardEntity>> getAwardsByType(@org.jetbrains.annotations.NotNull()
    java.lang.String type);
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertAward(@org.jetbrains.annotations.NotNull()
    com.studyon.app.data.entity.AwardEntity award, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateAward(@org.jetbrains.annotations.NotNull()
    com.studyon.app.data.entity.AwardEntity award, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAward(@org.jetbrains.annotations.NotNull()
    com.studyon.app.data.entity.AwardEntity award, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM awards WHERE type = :type")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAwardCountByType(@org.jetbrains.annotations.NotNull()
    java.lang.String type, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "DELETE FROM awards")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAllAwards(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}