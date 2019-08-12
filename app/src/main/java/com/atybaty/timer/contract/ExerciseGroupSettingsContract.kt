package com.atybaty.timer.contract

import com.atybaty.timer.model.ExerciseGroup
import com.atybaty.timer.utils.Seconds

interface ExerciseGroupSettingsContract {

    interface Presenter{
        fun fragmentViewCreated()
        fun backButtonClicked()
        fun saveButtonClicked()
        fun startTimeSet(time: Seconds)
        fun defaultTimeSet(time: Seconds)
        fun relaxTimeSet(time: Seconds)
        fun repeatsCountSet(count: Int)
        fun changeWorkButtonClicked()
    }

    interface View{
        fun showExerciseGroup(exerciseGroup: ExerciseGroup)
        fun updateStartTime(time: Seconds)
        fun updateDefaultTime(time: Seconds)
        fun updateRelaxTime(time: Seconds)
        fun updateRepeatscCount(count: Int)
        fun showChangeMessage()
        fun showPreviousScreen()
        fun showExerciseSettings()
    }
}