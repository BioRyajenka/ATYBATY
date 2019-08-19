package com.atybaty.timer.model.repository

import com.atybaty.timer.model.ExerciseGroup
import com.atybaty.timer.model.Workout
import com.atybaty.timer.model.repository.room.AppDatabase
import com.atybaty.timer.model.repository.room.WorkoutModel
import com.atybaty.timer.utils.Seconds

class WorkoutRepository(val database: AppDatabase) : Repository {

    override fun getAllWorkouts(): List<Workout> {
        return database.workoutModelDao().all.map { it.getData() }
    }

    override fun getWorkoutById(id: Int): Workout {
        return database.workoutModelDao().getById(id).getData()
    }

    override fun deleteWorkoutById(id: Int) {
        val workout = getWorkoutById(id)
        val workoutModel = WorkoutModel(workout)
        database.workoutModelDao().delete(workoutModel)
    }

    override fun saveWorkout(workout: Workout) {
        val workoutModel = WorkoutModel(workout)
        database.workoutModelDao().update(workoutModel)
    }

    override fun createNewWorkout(name: String, warmUp: Seconds, exerciseGroups: List<ExerciseGroup>, coolDown: Seconds): Workout {
        val maxIdWorkout = getAllWorkouts().maxBy { it.id }
        val maxId = maxIdWorkout?.id ?: 0
        val workout = Workout(maxId + 1, name, warmUp, exerciseGroups.toMutableList(), coolDown)
        val workoutModel = WorkoutModel(workout)
        database.workoutModelDao().insert(workoutModel)
        return workout
    }

}
