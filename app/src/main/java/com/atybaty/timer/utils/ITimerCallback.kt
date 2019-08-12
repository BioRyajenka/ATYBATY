package com.atybaty.timer.utils

interface ITimerCallback {

    fun tick(seconds: Int)

    fun stop()

    fun pause(seconds: Int)
}