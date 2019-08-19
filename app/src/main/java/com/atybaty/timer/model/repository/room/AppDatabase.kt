package com.atybaty.timer.model.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [WorkoutModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun workoutModelDao(): WorkoutModelDao
}