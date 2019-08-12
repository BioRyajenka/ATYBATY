package com.atybaty.timer.presenter

import com.atybaty.timer.contract.ExerciseGroupSettingsContract
import com.atybaty.timer.dataholders.CurrentWorkoutHolder

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

    override fun addStartTimeButtonClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun minusStartTimeButtonClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addDefaultTimeButtonClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun minusDefaultTimeButtonClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addRelaxTimeButtonClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun minusRelaxTimeButtonClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addRepeatsButtonClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun minusRepeatsButtonClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun changeWorkButtonClicked() {
        view.showChangeMessage()
    }
}