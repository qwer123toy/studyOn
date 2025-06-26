package com.studyon.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "awards")
data class AwardEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String, // 배지 제목
    val name: String, // 배지 이름 (내부용)
    val description: String, // 배지 설명
    val type: String, // 조건 구분 (예: 연속 7일, 100시간 등)
    val isEarned: Boolean = false, // 획득 여부
    val earnedAt: String = "" // yyyy-MM-dd 획득일
) 