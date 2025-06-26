package com.studyon.app.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.studyon.app.data.dao.*
import com.studyon.app.data.entity.*

@Database(
    entities = [
        GoalEntity::class,
        TodoEntity::class,
        RoutineLogEntity::class,
        AwardEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class StudyOnDatabase : RoomDatabase() {
    abstract fun goalDao(): GoalDao
    abstract fun todoDao(): TodoDao
    abstract fun routineLogDao(): RoutineLogDao
    abstract fun awardDao(): AwardDao

    companion object {
        @Volatile
        private var INSTANCE: StudyOnDatabase? = null

        fun getDatabase(context: Context): StudyOnDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StudyOnDatabase::class.java,
                    "studyon_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
} 