package com.atybaty.timer.dao

import com.atybaty.timer.model.Workout
import org.dizitart.no2.objects.Cursor

interface RepositoryDao {
    fun getAllWorkouts(): Cursor<Workout>
    fun getWorkoutById(id: Int): Workout
    fun deleteWorkoutById(id: Int)
    fun saveWorkout(workout: Workout)
    fun createNewWorkout(): Workout
}