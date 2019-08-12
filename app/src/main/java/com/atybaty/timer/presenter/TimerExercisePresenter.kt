package com.atybaty.timer.presenter

import android.content.Context
import android.os.CountDownTimer
import com.atybaty.timer.CurrentWorkoutHolder
import com.atybaty.timer.contract.TimerContract
import com.atybaty.timer.utils.ITimerCallback
import com.atybaty.timer.utils.Seconds
import com.atybaty.timer.utils.TimerExercise

class TimerExercisePresenter(val view: TimerContract.View) : TimerContract.Presenter, ITimerCallback {

    //private val currentWorkout = CurrentWorkoutHolder.currentWorkout
    private val currentWorkout = CurrentWorkoutHolder.createWorkout()

    private var pauseTag = TimerContract.Presenter.PauseButtonTag.PAUSE
    private var lockTag = TimerContract.Presenter.LockButtonTag.UNLOCK
    private var currentPosition = 0
    private var currenExerciseGroup = 0
    private lateinit var timer: TimerExercise
    private lateinit var context: Context

    override fun setContext(context: Context) {
        this.context = context
    }

    override fun activityCreated() {
        view.showExercises(currentWorkout)
        timer = updateTimer()
        timer.start()
    }

    override fun activityStopped() {
        timer.cancel()
    }

    override fun backButtonClicked() {
        view.showPreviousScreen()
    }

    override fun lockButtonClicked() {
        if (lockTag == TimerContract.Presenter.LockButtonTag.LOCK) {
            lockTag = TimerContract.Presenter.LockButtonTag.UNLOCK
        } else {
            lockTag = TimerContract.Presenter.LockButtonTag.LOCK
        }
        view.updateLockButton(lockTag)
    }

    override fun pauseButtonClicked() {
        if (pauseTag == TimerContract.Presenter.PauseButtonTag.PLAY) {
            pauseTag = TimerContract.Presenter.PauseButtonTag.PAUSE
            timer.start()
        } else {
            pauseTag = TimerContract.Presenter.PauseButtonTag.PLAY
            timer.onFinish()
            timer.cancel()
        }
        view.updatePauseButton(pauseTag)
    }

    override fun itemExerciseClicked(itemPosition: Int) {
        if (getDuration(itemPosition) >= 0){
            currentPosition = itemPosition
            currenExerciseGroup = getCurrentExerciseGroup()
            timer.cancel()
            timer = updateTimer()
            timer.start()
        }
    }

    override fun tick(seconds: Int) {
        view.updateTime(seconds)
    }

    override fun stop() {
        if (currentPosition < getItemCount()) {
            timer = updateTimer()
            timer.start()
        }else{
            view.updateTime(0)
        }
    }

    override fun pause(seconds: Int) {
        timer = TimerExercise(this, (seconds * 1000).toLong(), 1000)
    }

    private fun getCurrentExerciseGroup(): Int{
        var countItems = 0
        var numberExerciseGroup = -1

        for (i in 0 until currentWorkout.exerciseGroups.size){
            numberExerciseGroup++
            if (countItems == currentPosition){
                return numberExerciseGroup
            }
            for (j in 0 until currentWorkout.exerciseGroups[i].exercises.size){
                countItems++
                if (countItems == currentPosition){
                    return numberExerciseGroup
                }
            }
            countItems++
        }

        return numberExerciseGroup
    }

    private fun getNameExercise(itemPosition: Int): String {
        when (itemPosition) {
            getItemCount() - 1 -> return "Заминка"
            else -> {
                var count = 0
                for (i in 0 until currentWorkout.exerciseGroups.size) {
                    if (count == itemPosition) {
                        return currentWorkout.exerciseGroups[i].name
                    } else {
                        for (j in 0 until currentWorkout.exerciseGroups[i].exercises.size) {
                            count++
                            if (count == itemPosition) {
                                return currentWorkout.exerciseGroups[i].exercises[j].getName(context)
                            }
                        }
                    }
                    count++
                }
                return ""
            }
        }
    }

    private fun getDuration(itemPosition: Int): Seconds {
        when (itemPosition) {
            getItemCount() - 1 -> return currentWorkout.coolDown
            else -> {
                var count = 0
                for (i in 0 until currentWorkout.exerciseGroups.size) {
                    if (count == itemPosition) {
                        return -1
                    } else {
                        for (j in 0 until currentWorkout.exerciseGroups[i].exercises.size) {
                            count++
                            if (count == itemPosition) {
                                return currentWorkout.exerciseGroups[i].exercises[j].duration
                            }
                        }
                    }
                    count++
                }
                return -1
            }
        }
    }

    private fun updateTimer(): TimerExercise {
        if (getDuration(currentPosition) < 0) {
            view.updateExerciseGroupName(currentWorkout.exerciseGroups[currenExerciseGroup].name)
            currenExerciseGroup++
            currentPosition++
            return updateTimer()
        } else {
            view.updateCurrentExerciseFromList(currentPosition)
            view.updateExerciseName(getNameExercise(currentPosition))
            val duration = getDuration(currentPosition)
            currentPosition++
            return TimerExercise(this, (duration * 1000).toLong(), 1000)
        }
    }

    private fun getItemCount(): Int {
        //count exercise with coolDown
        var itemCount = 1
        currentWorkout.exerciseGroups.forEach {
            itemCount += it.exercises.size + 1
        }
        return itemCount
    }
}