package com.atybaty.timer.contract

interface ExerciseSettingsContract {

    interface Presenter{
        fun backButtonClicked()
        fun saveButtonClicked()
        fun addExerciseDurationClicked()
        fun minusExerciseDurationClicked()
        fun selectTypeClicked()
        fun addIntervalDuration()
        fun minusIntervalDuration()
        fun addAccelerationDuration()
        fun minusAccelerationDuration()
    }

    interface View{
        fun closeDialog()
        fun showSelectedType()
        fun showInterval()
        fun showAcceleration()
    }
}