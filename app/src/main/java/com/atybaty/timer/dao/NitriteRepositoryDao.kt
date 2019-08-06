package com.atybaty.timer.dao

import com.atybaty.timer.model.Workout
import org.dizitart.no2.objects.Cursor
import org.dizitart.no2.objects.ObjectRepository
import org.dizitart.no2.objects.filters.ObjectFilters.eq

class NitriteRepository(private val repository: ObjectRepository<Workout>):RepositoryDao {

    override fun getAllWorkouts(): Cursor<Workout> {
        return repository.find()
    }

    override fun getWorkoutById(id: Int): Workout {
        return repository.find(eq("id", id)).first()
    }

    override fun deleteWorkoutById(id: Int) {
        repository.remove(eq("id", id))
    }

    override fun saveWorkout(workout: Workout) {
        repository.update(eq("id", workout.id), workout)
    }

    override fun createNewWorkout(): Workout {
        val maxIdWorkout = repository.find().maxBy { it.id }
        val maxId = maxIdWorkout?.id ?: 0
        val workout = Workout(maxId + 1, "Default name", null, listOf(), null)
        repository.insert(workout)
        return workout
    }

}