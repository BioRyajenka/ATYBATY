package com.atybaty.timer.contract

import com.atybaty.timer.model.Exercise

interface TimerContract {

    interface Presenter{

        enum class PauseButtonTag{
            PLAY, PAUSE
        }

        fun activityCreated()
        fun backButtonClicked()
        fun lockButtonClicked()
        fun pauseButtonClicked()
    }

    interface View{
        fun updateTime(time: Long)
        fun updatePauseButton(tag: Presenter.PauseButtonTag)
        fun showPreviousScreen()
        fun showExercises(exercises: List<Exercise>)
        fun updateExerciseName(name: String)
        fun updateExerciseGroupName(name: String)
        fun updateCurrentExerciseFromList(itemPosition: Int)
    }
}