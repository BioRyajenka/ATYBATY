package com.atybaty.timer.presenter

import android.content.Context
import com.atybaty.timer.R
import com.atybaty.timer.contract.WorkoutContract
import com.atybaty.timer.dataholders.CurrentWorkoutHolder
import com.atybaty.timer.utils.Seconds
import com.atybaty.timer.dataholders.WorkoutRepositoryHolder

import com.atybaty.timer.model.ExerciseGroup
import com.atybaty.timer.model.Workout
import com.atybaty.timer.model.repository.WorkoutRepository
import com.atybaty.timer.view.workout.WorkoutFragment

class WorkoutPresenter(private val view: WorkoutFragment, private val context: Context) : WorkoutContract.Presenter {

    private lateinit var workout: Workout
    private lateinit var workoutRepository: WorkoutRepository

    override fun saveButtonClicked() {
        workoutRepository.saveWorkout(CurrentWorkoutHolder.currentWorkout)
    }

    override fun fragmentViewDestroyed() {
    }


    override fun warmUpDurationSet(duration: Seconds) {
        workout.warmUp = duration
    }

    override fun coolDownDurationSet(duration: Seconds) {
        workout.coolDown = duration
    }

    override fun exerciseGroupClicked(itemPosition: Int) {
        val exerciseGroup = workout.exerciseGroups[itemPosition]
        CurrentWorkoutHolder.currentExerciseGroup = exerciseGroup
        view.showExerciseGroup(exerciseGroup)
    }

    override fun addExerciseGroupButtonClicked() {
        val exerciseGroup = ExerciseGroup(R.string.exercise_group_default_name.toString()
                + workout.exerciseGroups.size.toString(), mutableListOf()
        )
        workout.exerciseGroups.add(exerciseGroup)
        view.showWorkout(workout)
    }

    override fun deleteButtonClicked(itemPosition: Int) {
        workout.exerciseGroups.removeAt(itemPosition)
        workoutRepository.saveWorkout(workout)
        view.showWorkout(this.workout)
    }

    override fun fragmentViewCreated() {
        this.workout = CurrentWorkoutHolder.currentWorkout
        this.workoutRepository = WorkoutRepositoryHolder.getWorkoutRepository(context)
        view.showWorkout(this.workout)
    }
}
