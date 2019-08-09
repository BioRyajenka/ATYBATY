package com.atybaty.timer.contract

import android.content.Context
import com.atybaty.timer.model.ExerciseGroup
import com.atybaty.timer.utils.Seconds

interface ExerciseGroupContract {

    interface Presenter {
        fun fragmentViewCreated(context: Context)
        fun fragmentDestroyed()

        fun backButtonClicked()
        fun saveButtonClicked()
        fun addWorkButtonClicked()
        fun addRestButtonClicked()
        fun setUpExerciseButtonClicked(itemPosition: Int)

        fun exerciseDurationSet(exerciseItemPosition: Int, newDuration: Seconds)
    }

    interface View {
        fun showExerciseGroup(exerciseGroup: ExerciseGroup)
        fun showExerciseSettings()
        fun returnToPreviousFragment()
    }
}
