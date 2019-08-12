package com.atybaty.timer.presenter

import com.atybaty.timer.contract.ExerciseGroupSettingsContract
import com.atybaty.timer.dataholders.CurrentWorkoutHolder
import com.atybaty.timer.utils.Seconds

class ExerciseGroupSettingsPresenter(val view: ExerciseGroupSettingsContract.View): ExerciseGroupSettingsContract.Presenter {

    private val currentExerciseGroup = CurrentWorkoutHolder.currentExerciseGroup

    override fun activityCreated() {

    }

    override fun backButtonClicked() {
        view.showPreviousScreen()
    }

    override fun saveButtonClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun startTimeSet(time: Seconds) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun defaultTimeSet(time: Seconds) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun relaxTimeSet(time: Seconds) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun repeatsCountSet(count: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun changeWorkButtonClicked() {
        view.showExerciseSettings()
    }
}