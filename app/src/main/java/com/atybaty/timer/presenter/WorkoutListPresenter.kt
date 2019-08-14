package com.atybaty.timer.presenter

import android.content.Context
import android.widget.Toast
import com.atybaty.timer.R
import com.atybaty.timer.contract.WorkoutListContract
import com.atybaty.timer.dataholders.CurrentWorkoutHolder
import com.atybaty.timer.dataholders.WorkoutRepositoryHolder
import com.atybaty.timer.model.Workout
import com.atybaty.timer.model.repository.WorkoutRepository

class WorkoutListPresenter(private val view: WorkoutListContract.View) : WorkoutListContract.Presenter {
    private lateinit var context: Context
    private lateinit var workoutRepository: WorkoutRepository
    private lateinit var workouts: MutableList<Workout>

    private fun viewShowWorkouts() {
        if (workouts.isEmpty()) {
            view.showEmptyMessage()
        } else {
            view.showWorkoutsList(workouts)
        }
    }

    override fun activityResumed(context: Context) {
        this.context = context
        this.workoutRepository = WorkoutRepositoryHolder.getWorkoutRepository(context)
        this.workouts = workoutRepository.getAllWorkouts().toMutableList()

        viewShowWorkouts()
    }

    override fun addButtonClicked() {
        val newWorkout = workoutRepository.createNewWorkout(
            name = context.getString(R.string.default_workout_name),
            warmUp = context.resources.getInteger(R.integer.default_warmup_duration_in_seconds),
            exerciseGroups = listOf(),
            coolDown = context.resources.getInteger(R.integer.default_cooldown_duration_in_seconds)
        )

        view.showWorkout(newWorkout)
    }

    override fun deleteButtonClicked(itemPosition: Int) {
        val deletedWorkout = workouts.removeAt(itemPosition)
        workoutRepository.deleteWorkoutById(deletedWorkout.id)
        viewShowWorkouts()
    }

    override fun playButtonClicked(itemPosition: Int) {
        val workout = workouts[itemPosition]

        if (workout.exerciseGroups.isEmpty()) {
            Toast.makeText(context, "Добавьте сетов в тренировку", Toast.LENGTH_SHORT).show()
            return
        }
        if (workout.exerciseGroups.any { it.exercises.isEmpty() }) {
            Toast.makeText(context, "В тренировке присутствуют пустые сеты", Toast.LENGTH_SHORT).show()
            return
        }

        CurrentWorkoutHolder.currentWorkout = workout
        view.showTimer(workout)
    }

    override fun itemClicked(itemPosition: Int) {
        CurrentWorkoutHolder.currentWorkout = workouts[itemPosition]
        view.showWorkout(workouts[itemPosition])
    }

    override fun activityDestroyed() {
        // maybe save to db, but not sure
    }
}
