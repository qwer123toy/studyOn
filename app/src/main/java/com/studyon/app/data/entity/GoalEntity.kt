package com.studyon.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "goals")
data class GoalEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val tag: String,
    val deadline: String, // yyyy-MM-dd
    val createdAt: String // yyyy-MM-dd
) 