package com.atybaty.timer.dataholders

import android.content.Context
import androidx.room.Room
import com.atybaty.timer.model.repository.WorkoutRepository
import com.atybaty.timer.model.repository.room.AppDatabase

private const val DB_FILE_NAME = "atybaty.db"

// see https://www.dizitart.org/nitrite-database/#tips
class WorkoutRepositoryHolder private constructor() {
    companion object {
        fun getWorkoutRepository(context: Context): WorkoutRepository {
            return singleton ?: synchronized(this) {
                singleton ?: WorkoutRepository(
                    buildRoomDB(
                        context
                    )
                ).also {
                    singleton = it
                }
            }
        }

        @Volatile
        private var singleton: WorkoutRepository? = null

        private fun buildRoomDB(context: Context): AppDatabase {
            val db = Room.databaseBuilder(
                context,
                AppDatabase::class.java, DB_FILE_NAME
            ).allowMainThreadQueries().build()
            return db
        }
    }
}
