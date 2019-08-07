package com.atybaty.timer.presenter

import android.content.Context
import com.atybaty.timer.CurrentWorkoutHolder
import com.atybaty.timer.R
import com.atybaty.timer.WorkoutRepositoryHolder
import com.atybaty.timer.contract.ExerciseGroupContract
import com.atybaty.timer.model.ExerciseGroup
import com.atybaty.timer.model.RestBetweenSets
import com.atybaty.timer.model.SimpleWorkOptions
import com.atybaty.timer.model.Work
import com.atybaty.timer.utils.Seconds

class ExerciseGroupPresenter(val view: ExerciseGroupContract.View, private val context: Context) : ExerciseGroupContract.Presenter {
    private val workoutRepository = WorkoutRepositoryHolder.getWorkoutRepository(context)
    private lateinit var exerciseGroup: ExerciseGroup

    override fun fragmentViewCreated(exerciseGroup: ExerciseGroup) {
        this.exerciseGroup = exerciseGroup
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

    override fun exerciseDurationSet(exerciseItemPosition: Int, newDuration: Seconds) {
        exerciseGroup.exercises[exerciseItemPosition].duration = newDuration
    }

}
