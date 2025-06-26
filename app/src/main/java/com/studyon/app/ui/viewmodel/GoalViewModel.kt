package com.studyon.app.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.studyon.app.data.database.StudyOnDatabase
import com.studyon.app.data.entity.GoalEntity
import com.studyon.app.data.entity.TodoEntity
import com.studyon.app.data.repository.StudyOnRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class GoalViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: StudyOnRepository

    val allGoals: LiveData<List<GoalEntity>>
    val allTodos: LiveData<List<TodoEntity>>

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    init {
        val database = StudyOnDatabase.getDatabase(application)
        repository = StudyOnRepository(
            database.goalDao(),
            database.todoDao(),
            database.routineLogDao(),
            database.awardDao()
        )
        
        allGoals = repository.getAllGoals()
        allTodos = repository.getAllTodos()
    }

    fun insertGoal(title: String, tag: String, deadline: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val today = formatter.format(Date())
                val goal = GoalEntity(
                    title = title,
                    tag = tag,
                    deadline = deadline,
                    createdAt = today
                )
                val goalId = repository.insertGoal(goal).toInt()
                
                // 목표 추가 시 기본 Todo 생성
                val defaultTodo = TodoEntity(
                    goalId = goalId,
                    content = "${title} 공부하기",
                    dueDate = today,
                    repeatType = "daily"
                )
                repository.insertTodo(defaultTodo)
                
                _message.value = "목표와 기본 루틴이 추가되었습니다!"
            } catch (e: Exception) {
                _message.value = "목표 추가에 실패했습니다: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun insertTodo(goalId: Int, content: String, dueDate: String, repeatType: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val todo = TodoEntity(
                    goalId = goalId,
                    content = content,
                    dueDate = dueDate,
                    repeatType = repeatType
                )
                repository.insertTodo(todo)
                _message.value = "할 일이 추가되었습니다!"
            } catch (e: Exception) {
                _message.value = "할 일 추가에 실패했습니다: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteGoal(goal: GoalEntity) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.deleteGoal(goal)
                _message.value = "목표가 삭제되었습니다!"
            } catch (e: Exception) {
                _message.value = "목표 삭제에 실패했습니다: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteTodo(todo: TodoEntity) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.deleteTodo(todo)
                _message.value = "할 일이 삭제되었습니다!"
            } catch (e: Exception) {
                _message.value = "할 일 삭제에 실패했습니다: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun toggleTodoStatus(todoId: Int, isDone: Boolean) {
        viewModelScope.launch {
            repository.updateTodoStatus(todoId, isDone)
        }
    }

    fun getTodosByGoal(goalId: Int): LiveData<List<TodoEntity>> {
        return repository.getTodosByGoal(goalId)
    }
} 