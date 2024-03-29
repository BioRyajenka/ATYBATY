package com.atybaty.timer.model.repository

import com.atybaty.timer.model.ExerciseGroup
import com.atybaty.timer.model.Workout
import com.atybaty.timer.util.Seconds

interface Repository {
    fun getAllWorkouts(): List<Workout>
    fun getWorkoutById(id: Int): Workout
    fun deleteWorkoutById(id: Int)
    fun saveWorkout(workout: Workout)
    fun createNewWorkout(name: String, exerciseGroups: List<ExerciseGroup>): Workout
}
