package com.atybaty.timer.util

interface SecondsTimerCallback {
    fun onTick(remainingTime: Seconds)

    fun onFinish()
}
