package com.atybaty.timer.presenter

import android.content.Context
import com.atybaty.timer.contract.ExerciseGroupSettingsContract
import com.atybaty.timer.dataholders.CurrentWorkoutHolder
import com.atybaty.timer.dataholders.WorkoutRepositoryHolder
import com.atybaty.timer.model.*
import com.atybaty.timer.model.repository.WorkoutRepository
import com.atybaty.timer.util.Seconds

class ExerciseGroupSettingsPresenter(val view: ExerciseGroupSettingsContract.View): ExerciseGroupSettingsContract.Presenter {

    private lateinit var currentExerciseGroup: ExerciseGroup
    private lateinit var workoutRepository: WorkoutRepository

    override fun fragmentViewCreated(context: Context) {
        currentExerciseGroup = CurrentWorkoutHolder.currentWorkout
            .exerciseGroups[CurrentWorkoutHolder.currentExerciseGroupPosition].deepCopy()
        workoutRepository = WorkoutRepositoryHolder.getWorkoutRepository(context)
        view.showExerciseGroup(currentExerciseGroup)
        if (hasChanges()){
            view.showChangeMessage()
        }else{
            view.hideChangeMessage()
        }
    }

    override fun backButtonClicked() {
        view.showPreviousScreen()
    }

    override fun saveButtonClicked() {
        saveExerciseWorkout()
        view.showPreviousScreen()
    }

    override fun startTimeSet(time: Seconds) {
        currentExerciseGroup.runUpDuration = time
    }

    override fun defaultTimeSet(time: Seconds) {
        currentExerciseGroup.defaultWorkDuration = time
        currentExerciseGroup.exercises.forEach {
            if (it is Work){
                it.duration = time
            }
        }
    }

    override fun relaxTimeSet(time: Seconds) {
        currentExerciseGroup.relaxDuration = time
        currentExerciseGroup.exercises.forEach {
            if (it is Relaxation){
                it.duration = time
            }
        }
    }

    override fun repeatsCountSet(count: Int) {
        currentExerciseGroup.repeatsCount = count
        refreshExercises()
    }

    override fun changeWorkButtonClicked() {
        refreshExercises()
        view.showExerciseSettings()
    }

    private fun hasChanges(): Boolean{
        currentExerciseGroup.exercises.forEach {
            if (it is Work && it.duration != currentExerciseGroup.defaultWorkDuration){
                return true
            }
            if (it is Relaxation && it.duration != currentExerciseGroup.relaxDuration){
                return true
            }
        }
        return false
    }

    private fun saveExerciseWorkout(){
        CurrentWorkoutHolder.currentWorkout.exerciseGroups[CurrentWorkoutHolder.currentExerciseGroupPosition] =
            currentExerciseGroup.deepCopy()
        workoutRepository.saveWorkout(CurrentWorkoutHolder.currentWorkout)
    }

    private fun refreshExercises(){
        if (currentExerciseGroup.exercises.size > currentExerciseGroup.repeatsCount * 2){
            val deletingExercise = ArrayList<Exercise>()
            for (i in currentExerciseGroup.repeatsCount * 2 until currentExerciseGroup.exercises.size){
                deletingExercise.add(currentExerciseGroup.exercises[i])
            }
            currentExerciseGroup.exercises.removeAll(deletingExercise)
        }
        if (currentExerciseGroup.exercises.size < currentExerciseGroup.repeatsCount * 2){
            val count = currentExerciseGroup.repeatsCount - currentExerciseGroup.exercises.size / 2
            for (i in 0 until count){
                currentExerciseGroup.exercises.add(Work("Работа", currentExerciseGroup.defaultWorkDuration, SimpleWorkOptions))
                currentExerciseGroup.exercises.add(CalmDown(currentExerciseGroup.relaxDuration))
            }
        }
        saveExerciseWorkout()
    }
}