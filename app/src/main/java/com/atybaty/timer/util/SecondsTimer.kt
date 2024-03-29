package com.atybaty.timer.util

import android.os.CountDownTimer
import kotlin.properties.Delegates

private const val TIMER_CORRECTION = 500

/**
 * Timer counts downto zero (so callback.onTick is also called on zero)
 */
class SecondsTimer(private val callback: SecondsTimerCallback) {
    constructor(countDownTime: Seconds, callback: SecondsTimerCallback) : this(callback) {
        setTime(countDownTime)
    }

    private lateinit var nestedTimer: CountDownTimer
    private var millisRemaining: Long by Delegates.notNull()
    private var isTimerRunning = false

    private val lock = Any()

    fun pause() = synchronized(lock) {
        if (!isTimerRunning) return
        nestedTimer.cancel()
        isTimerRunning = false
    }

    fun play() = synchronized(lock) {
        if (isTimerRunning) return
        nestedTimer = createNestedTimer(millisRemaining)
        nestedTimer.start()
        isTimerRunning = true
    }

    fun setTime(countDownTime: Seconds) = synchronized(lock) {
        check(!isTimerRunning) { "Timer should be stopped to be able to set time" }
        millisRemaining = countDownTime * MILLIS_IN_SECOND
        callback.onTick(countDownTime)
    }

    private fun createNestedTimer(countDownTimeInMillis: Long): CountDownTimer {
        return object : CountDownTimer(countDownTimeInMillis + TIMER_CORRECTION, MILLIS_IN_SECOND) {
            override fun onFinish() {
                pause()
                callback.onFinish()
            }

            override fun onTick(millisUntilFinished: Long) {
                if (millisRemaining < TIMER_CORRECTION) {
                    return
                }
                millisRemaining = millisUntilFinished
                callback.onTick((millisUntilFinished / MILLIS_IN_SECOND).toInt())
            }
        }
    }
}

interface SecondsTimerCallback {
    fun onTick(remainingTime: Seconds)

    fun onFinish()
}
