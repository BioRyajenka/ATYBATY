package com.atybaty.timer.presenter

import android.content.Context
import com.atybaty.timer.dataholders.CurrentWorkoutHolder
import com.atybaty.timer.R
import com.atybaty.timer.dataholders.WorkoutRepositoryHolder
import com.atybaty.timer.contract.ExerciseGroupContract
import com.atybaty.timer.model.ExerciseGroup
import com.atybaty.timer.model.RestBetweenSets
import com.atybaty.timer.model.SimpleWorkOptions
import com.atybaty.timer.model.Work
import com.atybaty.timer.model.repository.WorkoutRepository
import com.atybaty.timer.utils.Seconds

class ExerciseGroupPresenter(val view: ExerciseGroupContract.View) : ExerciseGroupContract.Presenter {
    private lateinit var context: Context

    private lateinit var workoutRepository: WorkoutRepository
    private lateinit var exerciseGroup: ExerciseGroup
    override fun fragmentViewCreated(exerciseGroup: ExerciseGroup, context: Context) {
        this.context = context
        this.exerciseGroup = exerciseGroup
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
        view.showExerciseSettings()
    }

    override fun exerciseDurationSet(exerciseItemPosition: Int, newDuration: Seconds, redraw: Boolean) {
        exerciseGroup.exercises[exerciseItemPosition].duration = newDuration
        if (redraw) {
            view.showExerciseGroup(exerciseGroup)
        }
    }

}
