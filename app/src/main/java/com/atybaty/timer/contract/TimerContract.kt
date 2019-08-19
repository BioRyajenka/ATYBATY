package com.atybaty.timer.contract

import android.content.Context
import android.graphics.Color
import com.atybaty.timer.model.Workout
import com.atybaty.timer.util.Seconds

interface TimerContract {

    interface Presenter {

        enum class PauseStatus {
            PLAYING, PAUSED, NOT_STARTED
        }

        enum class LockStatus {
            LOCK, UNLOCK
        }

        fun activityCreated(context: Context)
        fun activityStopped()
        fun backButtonClicked()
        fun lockButtonClicked()
        fun pauseButtonClicked()
        fun itemExerciseClicked(exerciseGroupIndex: Int, exerciseIndex: Int)
    }

    interface View {
        fun showPreviousScreen()
        fun showWorkout(workout: Workout)
        fun showNotificationAboutLock()

        fun updateTime(time: Seconds)
        fun updatePauseButton(status: Presenter.PauseStatus)
        fun updateLockButton(status: Presenter.LockStatus)
        fun updateCurrentExerciseSelection(exerciseGroupIndex: Int, exerciseIndex: Int)
        fun clearCurrentExerciseSelection()

        fun updateScreenColor(colorId: Int)
    }
}
