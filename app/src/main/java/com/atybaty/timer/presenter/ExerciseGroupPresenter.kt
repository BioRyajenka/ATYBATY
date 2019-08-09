package com.atybaty.timer.presenter

import android.content.Context
import com.atybaty.timer.dataholders.CurrentWorkoutHolder
import com.atybaty.timer.R
import com.atybaty.timer.dataholders.WorkoutRepositoryHolder
import com.atybaty.timer.contract.ExerciseGroupContract
import com.atybaty.timer.model.*
import com.atybaty.timer.model.repository.WorkoutRepository
import com.atybaty.timer.utils.Seconds

class ExerciseGroupPresenter(val view: ExerciseGroupContract.View) : ExerciseGroupContract.Presenter {
    private lateinit var context: Context
    private lateinit var workoutRepository: WorkoutRepository

    private lateinit var exerciseGroup: ExerciseGroup

    override fun fragmentViewCreated(context: Context) {
        this.context = context
        this.exerciseGroup = CurrentWorkoutHolder.currentExerciseGroup
        this.workoutRepository = WorkoutRepositoryHolder.getWorkoutRepository(context)

        view.showExerciseGroup(exerciseGroup)
    }

    override fun fragmentDestroyed() {

    }

    override fun backButtonClicked() {
        view.returnToPreviousFragment()
    }

    override fun saveButtonClicked() {
        workoutRepository.saveWorkout(CurrentWorkoutHolder.currentWorkout)
        view.returnToPreviousFragment()
    }

    override fun addWorkButtonClicked() {
        val workDuration = context.resources.getInteger(R.integer.default_work_duration_in_seconds)
        val work = Work("Работа", workDuration, SimpleWorkOptions())
        exerciseGroup.exercises.add(work)
        view.showExerciseGroup(exerciseGroup)
    }

    override fun addRestButtonClicked() {
        val rest = RestBetweenSets(context.resources.getInteger(R.integer.default_rest_duration_in_seconds))
        exerciseGroup.exercises.add(rest)
        view.showExerciseGroup(exerciseGroup)
    }

    override fun setUpExerciseButtonClicked(itemPosition: Int) {
        view.showExerciseSettings(itemPosition, exerciseGroup.exercises[itemPosition])
    }

    override fun exerciseUpdated(exerciseItemPosition: Int, newExercise: Exercise) {
        exerciseGroup.exercises[exerciseItemPosition] = newExercise
        view.updateExercise(exerciseItemPosition, exerciseGroup)
    }

    override fun exerciseDurationSet(exerciseItemPosition: Int, newDuration: Seconds) {
        exerciseGroup.exercises[exerciseItemPosition].duration = newDuration
    }

}
