package com.atybaty.timer.presenter

import android.content.Context
import com.atybaty.timer.R
import com.atybaty.timer.contract.WorkoutContract
import com.atybaty.timer.dataholders.CurrentWorkoutHolder
import com.atybaty.timer.util.Seconds
import com.atybaty.timer.dataholders.WorkoutRepositoryHolder
import com.atybaty.timer.model.*

import com.atybaty.timer.model.ExerciseGroup
import com.atybaty.timer.model.Workout
import com.atybaty.timer.model.repository.WorkoutRepository
import com.atybaty.timer.view.workoutsettings.workout.WorkoutFragment

class WorkoutPresenter(private val view: WorkoutFragment, private val context: Context) : WorkoutContract.Presenter {

    private lateinit var workout: Workout
    private lateinit var workoutRepository: WorkoutRepository

    override fun saveButtonClicked() {
        CurrentWorkoutHolder.currentWorkout = workout.deepCopy()
        workoutRepository.saveWorkout(CurrentWorkoutHolder.currentWorkout)
    }

    override fun timeAfterExererciseSet(itemPosition: Int, time: Seconds) {
        workout.exerciseGroups[itemPosition].relaxAfter.duration = time
    }

    override fun exerciseGroupClicked(itemPosition: Int) {
        CurrentWorkoutHolder.currentWorkout = workout.deepCopy()
        workoutRepository.saveWorkout(CurrentWorkoutHolder.currentWorkout)
        CurrentWorkoutHolder.currentExerciseGroupPosition = itemPosition
        view.showExerciseGroup()
    }

    override fun addExerciseGroupButtonClicked() {
        val work = Work("Работа", 60, SimpleWorkOptions)
        val relax = CalmDown(10)
        val exerciseGroup = ExerciseGroup(context.getString(R.string.exercise_group_default_name) + " "
                + (workout.exerciseGroups.size + 1).toString(), mutableListOf(work, relax)
        )
        workout.exerciseGroups.add(exerciseGroup)
        view.showWorkout(workout)
    }

    override fun deleteButtonClicked(itemPosition: Int) {
        workout.exerciseGroups.removeAt(itemPosition)
        view.showWorkout(this.workout)
    }

    override fun fragmentViewCreated() {
        this.workout = CurrentWorkoutHolder.currentWorkout.deepCopy()
        this.workoutRepository = WorkoutRepositoryHolder.getWorkoutRepository(context)
        view.showWorkout(this.workout)
    }
}
