package com.atybaty.timer.presenter

import android.content.Context
import android.widget.Toast
import com.atybaty.timer.R
import com.atybaty.timer.dataholders.WorkoutRepositoryHolder
import com.atybaty.timer.contract.WorkoutListContract
import com.atybaty.timer.dataholders.CurrentWorkoutHolder
import com.atybaty.timer.model.*

class WorkoutListPresenter(private val view: WorkoutListContract.View, private val context: Context) : WorkoutListContract.Presenter {
    private val workoutRepository = WorkoutRepositoryHolder.getWorkoutRepository(context)
    private lateinit var workouts: MutableList<Workout>

    private fun viewShowWorkouts() {
        workouts = workoutRepository.getAllWorkouts().toMutableList()
        if (workouts.isEmpty()) {
            view.showEmptyMessage()
        } else {
            view.showWorkoutsList(workouts)
        }
    }

    override fun activityResumed() {
        viewShowWorkouts()
    }

    override fun addButtonClicked() {
        val work = Work("Работа", 60, SimpleWorkOptions)
        val relax = CalmDown(10)
        val newWorkout = workoutRepository.createNewWorkout(
            name = context.getString(R.string.default_workout_name),
            exerciseGroups = listOf(ExerciseGroup("Сет 1", mutableListOf(work, relax))) // stub. TODO: change to emptyList()
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
        view.showTimer(workouts[itemPosition])
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
