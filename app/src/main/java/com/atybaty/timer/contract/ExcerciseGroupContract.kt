package com.atybaty.timer.contract

import com.atybaty.timer.model.ExerciseGroup
import com.atybaty.timer.utils.Seconds

interface ExerciseGroupContract {

    interface Presenter {
        fun fragmentViewCreated()
        fun fragmentViewDestroyed()

        fun backButtonClicked()
        fun saveButtonClicked()
        fun addExerciseButtonClicked()
        fun addRestButtonClicked()


        fun exerciseDurationSet(exerciseItemPosition: Int, newDuration: Seconds)
    }

    interface View {
        fun showExerciseGroup(exerciseGroup: ExerciseGroup)
        fun showExerciseSettings()
    }
}
