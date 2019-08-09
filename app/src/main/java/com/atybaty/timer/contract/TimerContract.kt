package com.atybaty.timer.contract

import com.atybaty.timer.model.Exercise

interface TimerContract {

    interface Presenter{
        fun activityCreated()
        fun backButtonClicked()
        fun lockButtonClicked()
        fun pauseButtonCLicked()
    }

    interface View{
        fun showTime(time: Long)
        fun updatePauseButton()
        fun showPreviousScreen()
        fun showExercises(exercises: ArrayList<Exercise>)
        fun showExerciseName(name: String)
        fun showExerciseGroupName(name: String)
        fun showCurrentExerciseFromList(itemPosition: Int)
    }
}