package com.atybaty.timer.contract

import com.atybaty.timer.model.Exercise
import com.atybaty.timer.model.Workout

interface TimerContract {

    interface Presenter{

        enum class PauseButtonTag{
            PLAY, PAUSE
        }

        fun activityCreated()
        fun backButtonClicked()
        fun lockButtonClicked()
        fun pauseButtonClicked()
        fun itemExerciseClicked(itemPosition: Int)
    }

    interface View{
        fun updateTime(time: Long)
        fun updatePauseButton(tag: Presenter.PauseButtonTag)
        fun showPreviousScreen()
        fun showExercises(workout: Workout)
        fun updateExerciseName(name: String)
        fun updateExerciseGroupName(name: String)
        fun updateCurrentExerciseFromList(itemPosition: Int)
    }
}