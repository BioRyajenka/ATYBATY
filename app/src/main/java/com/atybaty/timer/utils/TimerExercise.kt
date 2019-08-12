package com.atybaty.timer.utils

import android.os.CountDownTimer

class TimerExercise(val callback: ITimerCallback, millisInFuture: Long, countDownInterval: Long) :
    CountDownTimer(millisInFuture, countDownInterval){

    private var seconds = (millisInFuture / countDownInterval).toInt()

    override fun onFinish() {
        seconds--
        if (seconds == 0){
            callback.stop()
        }else{
            callback.pause(seconds)
        }
    }

    override fun onTick(p0: Long) {
        seconds = (p0 / 1000).toInt()
        callback.tick(seconds)
    }
}