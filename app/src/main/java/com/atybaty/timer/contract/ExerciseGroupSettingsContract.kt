package com.atybaty.timer.contract

import android.content.Context
import com.atybaty.timer.model.ExerciseGroup
import com.atybaty.timer.util.Seconds

interface ExerciseGroupSettingsContract {

    interface Presenter{
        fun fragmentViewCreated(context: Context)
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
        fun showChangeMessage()
        fun hideChangeMessage()
        fun showPreviousScreen()
        fun showExerciseSettings()
    }
}