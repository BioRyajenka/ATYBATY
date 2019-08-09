package com.atybaty.timer.contract

import android.content.Context
import com.atybaty.timer.model.Exercise
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
        fun exerciseUpdated(exerciseItemPosition: Int, newExercise: Exercise)
    }

    interface View {
        fun showExerciseGroup(exerciseGroup: ExerciseGroup)
        fun showExerciseSettings(exerciseItemPosition: Int, exercise: Exercise)
        fun updateExercise(exerciseItemPosition: Int, exerciseGroup: ExerciseGroup)
        fun returnToPreviousFragment()
    }
}
