package com.atybaty.timer.contract

import com.atybaty.timer.utils.Seconds

interface ExerciseGroupSettingsContract {

    interface Presenter{
        fun activityCreated()
        fun backButtonClicked()
        fun saveButtonClicked()
        fun addStartTimeButtonClicked()
        fun minusStartTimeButtonClicked()
        fun addDefaultTimeButtonClicked()
        fun minusDefaultTimeButtonClicked()
        fun addRelaxTimeButtonClicked()
        fun minusRelaxTimeButtonClicked()
        fun addRepeatsButtonClicked()
        fun minusRepeatsButtonClicked()
        fun changeWorkButtonClicked()
    }

    interface View{
        fun showStartTime(time: Seconds)
        fun showDefaultTime(time: Seconds)
        fun showRelaxTime(time: Seconds)
        fun showRepeatscCount(count: Int)
        fun showChangeMessage()
        fun showPreviousScreen()
        fun showExerciseSettings()
    }
}