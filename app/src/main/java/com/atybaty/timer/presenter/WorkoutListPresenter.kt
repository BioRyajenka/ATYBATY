package com.atybaty.timer.presenter

import android.content.Context
import android.widget.Toast
import com.atybaty.timer.R
import com.atybaty.timer.dataholders.WorkoutRepositoryHolder
import com.atybaty.timer.contract.WorkoutListContract
import com.atybaty.timer.dataholders.CurrentWorkoutHolder
import com.atybaty.timer.model.ExerciseGroup

class WorkoutListPresenter(private val view: WorkoutListContract.View, private val context: Context) : WorkoutListContract.Presenter {
    private val workoutRepository = WorkoutRepositoryHolder.getWorkoutRepository(context)
    private val workouts = workoutRepository.getAllWorkouts().toMutableList()

    private fun viewShowWorkouts() {
        if (workouts.isEmpty()) {
            view.showEmptyMessage()
        } else {
            view.showWorkoutsList(workouts)
        }
    }

    override fun activityCreated() {
        viewShowWorkouts()
    }

    override fun addButtonClicked() {
        val newWorkout = workoutRepository.createNewWorkout(
            name = context.getString(R.string.default_workout_name),
            warmUp = context.resources.getInteger(R.integer.default_warmup_duration_in_seconds),
            exerciseGroups = listOf(ExerciseGroup("Сет 1", mutableListOf())), // stub. TODO: change to emptyList()
            coolDown = context.resources.getInteger(R.integer.default_cooldown_duration_in_seconds)
        )

        CurrentWorkoutHolder.currentWorkout = newWorkout
        view.showWorkout(newWorkout)

        // this is stub
//        workouts.add(newWorkout)
//        viewShowWorkouts()
    }

    override fun deleteButtonClicked(itemPosition: Int) {
        val deletedWorkout = workouts.removeAt(itemPosition)
        workoutRepository.deleteWorkoutById(deletedWorkout.id)
        viewShowWorkouts()
    }

    override fun playButtonClicked(itemPosition: Int) {
        Toast.makeText(context, "TODO", Toast.LENGTH_SHORT).show()
    }

    override fun itemClicked(itemPosition: Int) {
        val workout = workouts[itemPosition]
        CurrentWorkoutHolder.currentWorkout = workout
        view.showWorkout(workout)
    }

    override fun activityDestroyed() {
        // maybe save to db, but not sure
    }
}
