package com.studyon.app.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b0\nH\'J\u0014\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b0\nH\'J\u001c\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b0\n2\u0006\u0010\u000e\u001a\u00020\u000fH\'J\u0016\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u001e\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0015H\u00a7@\u00a2\u0006\u0002\u0010\u0016\u00a8\u0006\u0017"}, d2 = {"Lcom/studyon/app/data/dao/TodoDao;", "", "deleteAllTodos", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteTodo", "todo", "Lcom/studyon/app/data/entity/TodoEntity;", "(Lcom/studyon/app/data/entity/TodoEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllTodos", "Landroidx/lifecycle/LiveData;", "", "getIncompleteTodos", "getTodosByGoal", "goalId", "", "insertTodo", "updateTodo", "updateTodoStatus", "todoId", "isDone", "", "(IZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
@androidx.room.Dao()
public abstract interface TodoDao {
    
    @androidx.room.Query(value = "SELECT * FROM todos ORDER BY dueDate ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.studyon.app.data.entity.TodoEntity>> getAllTodos();
    
    @androidx.room.Query(value = "SELECT * FROM todos WHERE goalId = :goalId ORDER BY dueDate ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.studyon.app.data.entity.TodoEntity>> getTodosByGoal(int goalId);
    
    @androidx.room.Query(value = "SELECT * FROM todos WHERE isDone = 0 ORDER BY dueDate ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<com.studyon.app.data.entity.TodoEntity>> getIncompleteTodos();
    
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertTodo(@org.jetbrains.annotations.NotNull()
    com.studyon.app.data.entity.TodoEntity todo, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateTodo(@org.jetbrains.annotations.NotNull()
    com.studyon.app.data.entity.TodoEntity todo, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteTodo(@org.jetbrains.annotations.NotNull()
    com.studyon.app.data.entity.TodoEntity todo, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE todos SET isDone = :isDone WHERE id = :todoId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateTodoStatus(int todoId, boolean isDone, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM todos")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAllTodos(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}