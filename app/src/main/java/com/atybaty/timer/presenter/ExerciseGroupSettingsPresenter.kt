package com.atybaty.timer.presenter

import android.content.Context
import android.util.Log
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
        currentExerciseGroup = CurrentWorkoutHolder.currentExerciseGroup.deepCopy()
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
        currentExerciseGroup.warmUp = time
    }

    override fun defaultTimeSet(time: Seconds) {
        currentExerciseGroup.defaultTime = time
        currentExerciseGroup.exercises.forEach {
            if (it is Work){
                it.duration = time
            }
        }
    }

    override fun relaxTimeSet(time: Seconds) {
        currentExerciseGroup.relaxTime = time
    }

    override fun repeatsCountSet(count: Int) {
        currentExerciseGroup.repeatsCount = count
        updateExercises()
    }

    override fun changeWorkButtonClicked() {
        updateExercises()
        view.showExerciseSettings()
    }

    private fun hasChanges(): Boolean{
        currentExerciseGroup.exercises.forEach {
            if (it is Work && it.duration != currentExerciseGroup.defaultTime){
                return true
            }
            if (it is Relaxation && it.duration != currentExerciseGroup.relaxTime){
                return true
            }
        }
        return false
    }

    private fun saveExerciseWorkout(){
        CurrentWorkoutHolder.currentExerciseGroup = currentExerciseGroup.deepCopy()
        CurrentWorkoutHolder.currentWorkout.exerciseGroups.set(CurrentWorkoutHolder.currentExerciseGroupPosition, CurrentWorkoutHolder.currentExerciseGroup)
        workoutRepository.saveWorkout(CurrentWorkoutHolder.currentWorkout)
    }

    private fun updateExercises(){
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
                currentExerciseGroup.exercises.add(Work("Работа", currentExerciseGroup.defaultTime, SimpleWorkOptions))
                currentExerciseGroup.exercises.add(CalmDown(currentExerciseGroup.relaxTime))
            }
        }
        saveExerciseWorkout()
    }
}