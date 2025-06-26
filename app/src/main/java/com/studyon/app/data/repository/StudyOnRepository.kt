package com.studyon.app.data.repository

import androidx.lifecycle.LiveData
import com.studyon.app.data.dao.*
import com.studyon.app.data.entity.*

class StudyOnRepository(
    private val goalDao: GoalDao,
    private val todoDao: TodoDao,
    private val routineLogDao: RoutineLogDao,
    private val awardDao: AwardDao
) {
    // Goal operations
    fun getAllGoals(): LiveData<List<GoalEntity>> = goalDao.getAllGoals()
    suspend fun insertGoal(goal: GoalEntity): Long = goalDao.insertGoal(goal)
    suspend fun updateGoal(goal: GoalEntity) = goalDao.updateGoal(goal)
    suspend fun deleteGoal(goal: GoalEntity) = goalDao.deleteGoal(goal)
    suspend fun getGoalById(goalId: Int): GoalEntity? = goalDao.getGoalById(goalId)

    // Todo operations
    fun getAllTodos(): LiveData<List<TodoEntity>> = todoDao.getAllTodos()
    fun getTodosByGoal(goalId: Int): LiveData<List<TodoEntity>> = todoDao.getTodosByGoal(goalId)
    fun getIncompleteTodos(): LiveData<List<TodoEntity>> = todoDao.getIncompleteTodos()
    suspend fun insertTodo(todo: TodoEntity) = todoDao.insertTodo(todo)
    suspend fun updateTodo(todo: TodoEntity) = todoDao.updateTodo(todo)
    suspend fun deleteTodo(todo: TodoEntity) = todoDao.deleteTodo(todo)
    suspend fun updateTodoStatus(todoId: Int, isDone: Boolean) = todoDao.updateTodoStatus(todoId, isDone)

    // RoutineLog operations
    fun getAllRoutineLogs(): LiveData<List<RoutineLogEntity>> = routineLogDao.getAllRoutineLogs()
    fun getRoutineLogsByDate(date: String): LiveData<List<RoutineLogEntity>> = routineLogDao.getRoutineLogsByDate(date)
    fun getRoutineLogsByGoal(goalId: Int): LiveData<List<RoutineLogEntity>> = routineLogDao.getRoutineLogsByGoal(goalId)
    suspend fun getTotalDurationByDate(date: String): Int = routineLogDao.getTotalDurationByDate(date) ?: 0
    fun getRoutineLogsBetweenDates(startDate: String, endDate: String): LiveData<List<RoutineLogEntity>> = 
        routineLogDao.getRoutineLogsBetweenDates(startDate, endDate)
    suspend fun insertRoutineLog(routineLog: RoutineLogEntity) = routineLogDao.insertRoutineLog(routineLog)
    suspend fun updateRoutineLog(routineLog: RoutineLogEntity) = routineLogDao.updateRoutineLog(routineLog)
    suspend fun deleteRoutineLog(routineLog: RoutineLogEntity) = routineLogDao.deleteRoutineLog(routineLog)

    // Award operations
    fun getAllAwards(): LiveData<List<AwardEntity>> = awardDao.getAllAwards()
    fun getAwardsByType(type: String): LiveData<List<AwardEntity>> = awardDao.getAwardsByType(type)
    suspend fun insertAward(award: AwardEntity) = awardDao.insertAward(award)
    suspend fun updateAward(award: AwardEntity) = awardDao.updateAward(award)
    suspend fun deleteAward(award: AwardEntity) = awardDao.deleteAward(award)
    suspend fun getAwardCountByType(type: String): Int = awardDao.getAwardCountByType(type)
} 