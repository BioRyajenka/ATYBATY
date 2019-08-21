package com.atybaty.timer.presenter

import android.content.Context
import com.atybaty.timer.dataholders.CurrentWorkoutHolder
import com.atybaty.timer.dataholders.WorkoutRepositoryHolder
import com.atybaty.timer.contract.ExerciseGroupContract
import com.atybaty.timer.model.*
import com.atybaty.timer.model.repository.WorkoutRepository
import com.atybaty.timer.util.Seconds
import kotlinx.android.synthetic.main.fragment_set.*

class ExerciseGroupPresenter(val view: ExerciseGroupContract.View) : ExerciseGroupContract.Presenter {
    private lateinit var context: Context
    private lateinit var workoutRepository: WorkoutRepository

    private lateinit var exerciseGroup: ExerciseGroup

    override fun fragmentViewCreated(context: Context) {
        this.context = context
        this.exerciseGroup = CurrentWorkoutHolder.currentWorkout
            .exerciseGroups[CurrentWorkoutHolder.currentExerciseGroupPosition].deepCopy()
        this.workoutRepository = WorkoutRepositoryHolder.getWorkoutRepository(context)

        view.showExerciseGroup(exerciseGroup)
        view.changeButtonState(checkDefaultButtonActive())
    }

    override fun fragmentDestroyed() {

    }

    override fun backButtonClicked() {
        view.returnToPreviousFragment()
    }

    override fun saveButtonClicked() {
        CurrentWorkoutHolder.currentWorkout.exerciseGroups
            .set(CurrentWorkoutHolder.currentExerciseGroupPosition, exerciseGroup.deepCopy())
        workoutRepository.saveWorkout(CurrentWorkoutHolder.currentWorkout)
        view.returnToPreviousFragment()
    }

    override fun setUpDefaultButtonClicked() {
        for (i in 0 until exerciseGroup.exercises.size) {
            if (exerciseGroup.exercises[i] is Work) {
                val work = exerciseGroup.exercises[i] as Work
                work.duration = exerciseGroup.defaultTime
                work.options = SimpleWorkOptions
            }
            if (exerciseGroup.exercises[i] is Relaxation) {
                val relax = exerciseGroup.exercises[i] as CalmDown
                relax.duration = exerciseGroup.relaxTime
            }
        }
        view.changeButtonState(false)
        view.showExerciseGroup(exerciseGroup)
    }

    private fun checkDefaultButtonActive(): Boolean {
        var isChanged = false;
        for (i in 0 until exerciseGroup.exercises.size) {
            if (exerciseGroup.exercises[i] is Work) {
                val work = exerciseGroup.exercises[i] as Work
                isChanged = isChanged || work.duration != exerciseGroup.defaultTime || work.options !is SimpleWorkOptions
            }
            if (exerciseGroup.exercises[i] is Relaxation) {
                val relax = exerciseGroup.exercises[i] as CalmDown
                isChanged = isChanged || (relax.duration != exerciseGroup.relaxTime)
            }
        }
        return isChanged
    }

    override fun setUpExerciseButtonClicked(itemPosition: Int) {
        CurrentWorkoutHolder.currentWorkPosition = itemPosition
        view.showExerciseSettings(itemPosition, exerciseGroup.exercises[itemPosition])
    }

    override fun exerciseUpdated(exerciseItemPosition: Int, newExercise: Exercise) {
        exerciseGroup.exercises[exerciseItemPosition] = newExercise
        view.updateExercise(exerciseItemPosition, exerciseGroup)
        view.changeButtonState(checkDefaultButtonActive())
    }

    override fun exerciseDurationSet(exerciseItemPosition: Int, newDuration: Seconds) {
        view.changeButtonState(checkDefaultButtonActive())
        exerciseGroup.exercises[exerciseItemPosition].duration = newDuration
    }

}
