package com.studyon.app.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.studyon.app.data.entity.TodoEntity

@Dao
interface TodoDao {
    @Query("SELECT * FROM todos ORDER BY dueDate ASC")
    fun getAllTodos(): LiveData<List<TodoEntity>>

    @Query("SELECT * FROM todos WHERE goalId = :goalId ORDER BY dueDate ASC")
    fun getTodosByGoal(goalId: Int): LiveData<List<TodoEntity>>

    @Query("SELECT * FROM todos WHERE isDone = 0 ORDER BY dueDate ASC")
    fun getIncompleteTodos(): LiveData<List<TodoEntity>>

    @Insert
    suspend fun insertTodo(todo: TodoEntity)

    @Update
    suspend fun updateTodo(todo: TodoEntity)

    @Delete
    suspend fun deleteTodo(todo: TodoEntity)

    @Query("UPDATE todos SET isDone = :isDone WHERE id = :todoId")
    suspend fun updateTodoStatus(todoId: Int, isDone: Boolean)

    @Query("DELETE FROM todos")
    suspend fun deleteAllTodos()
} 