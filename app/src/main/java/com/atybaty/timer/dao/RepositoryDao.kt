package com.atybaty.timer.dao

import com.atybaty.timer.model.Workout

interface RepositoryDao {
    fun getAllWorkouts(): List<Workout>
    fun getWorkoutById(id: Int): Workout
    fun deleteWorkoutById(id: Int)
    fun saveWorkout(workout: Workout)
    fun createNewWorkout(): Workout
}