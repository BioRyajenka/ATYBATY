package com.atybaty.timer.presenter

import android.content.Context
import com.atybaty.timer.R
import com.atybaty.timer.contract.WorkoutContract
import com.atybaty.timer.utils.Seconds

import com.atybaty.timer.model.ExerciseGroup
import com.atybaty.timer.model.Workout
import com.atybaty.timer.view.workout.WorkoutFragment

class WorkoutPresenter(private val view: WorkoutFragment, private val context: Context) : WorkoutContract.Presenter {

    private lateinit var workout: Workout

    override fun saveButtonClicked() {
        TODO("Connect with igors code")
//        workoutRepository.saveWorkout(CurrentWorkoutHolder.currentWorkout)
//        view.returnToPreviousFragment()
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addExerciseGroupButtonClicked() {
        val exerciseGroup = ExerciseGroup(R.string.exercise_group_default_name.toString()
                + workout.exerciseGroups.size.toString(), arrayListOf()
        )
        workout.exerciseGroups.add(exerciseGroup)
        view.showWorkout(workout)
    }

    override fun fragmentViewCreated(workout: Workout) {
        this.workout = workout
        view.showWorkout(this.workout)
    }
}
