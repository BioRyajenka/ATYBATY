package com.atybaty.timer.contract

import android.content.Context
import com.atybaty.timer.model.Exercise
import com.atybaty.timer.model.Workout
import com.atybaty.timer.utils.Seconds

interface TimerContract {

    interface Presenter{

        enum class PauseButtonTag{
            PLAY, PAUSE
        }

        enum class LockButtonTag{
            LOCK, UNLOCK
        }
        fun setContext(context: Context)
        fun activityCreated()
        fun activityStopped()
        fun backButtonClicked()
        fun lockButtonClicked()
        fun pauseButtonClicked()
        fun itemExerciseClicked(itemPosition: Int)
    }

    interface View{
        fun updateTime(time: Seconds)
        fun updatePauseButton(tag: Presenter.PauseButtonTag)
        fun updateLockButton(tag: Presenter.LockButtonTag)
        fun showPreviousScreen()
        fun showExercises(workout: Workout)
        fun updateExerciseName(name: String)
        fun updateExerciseGroupName(name: String)
        fun updateCurrentExerciseFromList(itemPosition: Int)
    }
}