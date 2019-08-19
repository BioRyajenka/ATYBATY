package com.atybaty.timer.presenter

import android.content.Context
import com.atybaty.timer.dataholders.CurrentWorkoutHolder
import com.atybaty.timer.R
import com.atybaty.timer.dataholders.WorkoutRepositoryHolder
import com.atybaty.timer.contract.ExerciseGroupContract
import com.atybaty.timer.model.*
import com.atybaty.timer.model.repository.WorkoutRepository
import com.atybaty.timer.util.Seconds

class ExerciseGroupPresenter(val view: ExerciseGroupContract.View) : ExerciseGroupContract.Presenter {
    private lateinit var context: Context
    private lateinit var workoutRepository: WorkoutRepository

    private lateinit var exerciseGroup: ExerciseGroup

    override fun fragmentViewCreated(context: Context) {
        this.context = context
        this.exerciseGroup = CurrentWorkoutHolder.currentExerciseGroup.deepCopy()
        this.workoutRepository = WorkoutRepositoryHolder.getWorkoutRepository(context)

        view.showExerciseGroup(exerciseGroup)
    }

    override fun fragmentDestroyed() {

    }

    override fun backButtonClicked() {
        view.returnToPreviousFragment()
    }

    override fun saveButtonClicked() {
        val position = CurrentWorkoutHolder.currentWorkout.exerciseGroups.indexOf(CurrentWorkoutHolder.currentExerciseGroup)
        CurrentWorkoutHolder.currentExerciseGroup = exerciseGroup.deepCopy()
        CurrentWorkoutHolder.currentWorkout.exerciseGroups.set(position, CurrentWorkoutHolder.currentExerciseGroup)
        workoutRepository.saveWorkout(CurrentWorkoutHolder.currentWorkout)
        view.returnToPreviousFragment()
    }

    override fun setUpDefaultButtonClicked() {
        updateExercise()
        view.showExerciseGroup(exerciseGroup)
    }

    override fun setUpExerciseButtonClicked(itemPosition: Int) {
        CurrentWorkoutHolder.currentWork = exerciseGroup.exercises[itemPosition] as Work
        view.showExerciseSettings(itemPosition, exerciseGroup.exercises[itemPosition])
    }

    override fun exerciseUpdated(exerciseItemPosition: Int, newExercise: Exercise) {
        exerciseGroup.exercises[exerciseItemPosition] = newExercise
        view.updateExercise(exerciseItemPosition, exerciseGroup)
    }

    override fun exerciseDurationSet(exerciseItemPosition: Int, newDuration: Seconds) {
        exerciseGroup.exercises[exerciseItemPosition].duration = newDuration
    }

    private fun updateExercise(){
        for (i in 0 until exerciseGroup.exercises.size){
            if (exerciseGroup.exercises[i] is Work){
                val work = exerciseGroup.exercises[i] as Work
                work.duration = exerciseGroup.defaultTime
                work.options = SimpleWorkOptions
                exerciseGroup.exercises[i] = work
            }
            if (exerciseGroup.exercises[i] is Relaxation){
                val relax = exerciseGroup.exercises[i] as CalmDown
                relax.duration = exerciseGroup.relaxTime
                exerciseGroup.exercises[i] = relax
            }
        }
    }

}
