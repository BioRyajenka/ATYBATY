package com.atybaty.timer.presenter

import android.content.Context
import com.atybaty.timer.R
import com.atybaty.timer.contract.TimerContract
import com.atybaty.timer.contract.TimerContract.Presenter.LockStatus
import com.atybaty.timer.contract.TimerContract.Presenter.PauseStatus
import com.atybaty.timer.dataholders.CurrentWorkoutHolder
import com.atybaty.timer.dataholders.SelectedSoundsHolder
import com.atybaty.timer.model.CalmDown
import com.atybaty.timer.model.RestBetweenSets
import com.atybaty.timer.model.RunUp
import com.atybaty.timer.model.Work
import com.atybaty.timer.util.AudioPlayer
import com.atybaty.timer.util.Seconds
import com.atybaty.timer.util.SecondsTimer
import com.atybaty.timer.util.SecondsTimerCallback

class TimerExercisePresenter(val view: TimerContract.View) : TimerContract.Presenter, SecondsTimerCallback {

    private val workout = CurrentWorkoutHolder.currentWorkout

    private var pauseStatus = PauseStatus.NOT_STARTED
    private var lockStatus = LockStatus.UNLOCK
    private var currentExerciseGroupIndex = 0
    private var currentExerciseIndex = 0
    private val timer: SecondsTimer = SecondsTimer(this)
    private lateinit var audioPlayer: AudioPlayer

    private lateinit var context: Context

    private fun synchronizeTimer(pauseStatus: PauseStatus) {
        if (pauseStatus == PauseStatus.PLAYING) {
            timer.play()
        } else {
            timer.pause()
        }
    }

    private fun synchronizeExerciseSelection(exerciseGroupIndex: Int, exerciseIndex: Int) {
        val exercise = workout.exerciseGroups[exerciseGroupIndex].extendedExercises[exerciseIndex]

        timer.pause()
        timer.setTime(exercise.duration)
        synchronizeTimer(pauseStatus)
        view.updateCurrentExerciseSelection(exerciseGroupIndex, exerciseIndex)

        val screenColor = when (exercise) {
            is Work -> R.color.timerWork
            is CalmDown -> R.color.timerRest
            is RestBetweenSets -> R.color.timerRestAfterSet
            is RunUp -> R.color.timerWarmUp
            else -> error("Unexpected work type")
        }
        view.updateScreenColor(screenColor)
    }

    override fun activityCreated(context: Context) {
        this.context = context
        this.audioPlayer = AudioPlayer(context)

        view.showWorkout(workout)
        view.updatePauseButton(pauseStatus)
        synchronizeExerciseSelection(currentExerciseGroupIndex, currentExerciseIndex)
    }

    override fun activityStopped() {
        timer.pause()
    }

    override fun backButtonClicked() {
        if (lockStatus == LockStatus.LOCK) {
            view.showNotificationAboutLock()
            return
        }

        timer.pause()
        view.showPreviousScreen()
    }

    override fun lockButtonClicked() {
        lockStatus = if (lockStatus == LockStatus.LOCK) {
            LockStatus.UNLOCK
        } else {
            LockStatus.LOCK
        }
        view.updateLockButton(lockStatus)
    }

    override fun pauseButtonClicked() {
        if (lockStatus == LockStatus.LOCK) {
            view.showNotificationAboutLock()
            return
        }

        pauseStatus = if (pauseStatus == PauseStatus.PLAYING) {
            PauseStatus.PAUSED
        } else {
            PauseStatus.PLAYING
        }
        synchronizeTimer(pauseStatus)
        view.updatePauseButton(pauseStatus)
    }

    override fun itemExerciseClicked(exerciseGroupIndex: Int, exerciseIndex: Int) {
        if (lockStatus == LockStatus.LOCK) {
            view.showNotificationAboutLock()
            return
        }

        this.currentExerciseGroupIndex = exerciseGroupIndex
        this.currentExerciseIndex = exerciseIndex
        synchronizeExerciseSelection(currentExerciseGroupIndex, currentExerciseIndex)

    }

    override fun onTick(remainingTime: Seconds) {
        if (remainingTime in (1..3)) {
            audioPlayer.playSound(SelectedSoundsHolder.timerExerciseEndsTick)
        }

        view.updateTime(remainingTime)
    }

    override fun onFinish() {
        currentExerciseIndex++
        if (currentExerciseIndex == workout.exerciseGroups[currentExerciseGroupIndex].exercises.size) {
            currentExerciseGroupIndex++
            currentExerciseIndex = 0
        }
        if (currentExerciseGroupIndex == workout.exerciseGroups.size) {
            audioPlayer.playSound(SelectedSoundsHolder.timerTrainingFinished)

            view.clearCurrentExerciseSelection()
            view.updateTime(0)
            pauseStatus = PauseStatus.PAUSED
            view.updatePauseButton(pauseStatus)
        } else {
            audioPlayer.playSound(SelectedSoundsHolder.timerExerciseFinished)

            synchronizeExerciseSelection(currentExerciseGroupIndex, currentExerciseIndex)
        }
    }
}
