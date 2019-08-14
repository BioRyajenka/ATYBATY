package com.atybaty.timer.model.repository

import com.atybaty.timer.model.ExerciseGroup
import com.atybaty.timer.model.Workout
import com.atybaty.timer.utils.Seconds
import org.dizitart.kno2.getRepository
import org.dizitart.no2.Nitrite
import org.dizitart.no2.objects.filters.ObjectFilters.eq

class WorkoutRepository(nitrite: Nitrite) : Repository {
    private val repository = nitrite.getRepository<Workout>()

    override fun getAllWorkouts(): List<Workout> {
        return repository.find().toList()
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

    override fun createNewWorkout(name: String, exerciseGroups: List<ExerciseGroup>): Workout {
        val maxIdWorkout = repository.find().maxBy { it.id }
        val maxId = maxIdWorkout?.id ?: 0
        val workout = Workout(maxId + 1, name, exerciseGroups.toMutableList())
        repository.insert(workout)
        return workout
    }

}
