package com.atybaty.timer.model

import android.content.Context
import com.atybaty.timer.R
import com.atybaty.timer.utils.Seconds
import java.time.Duration

sealed class Exercise(var duration: Seconds) {
    abstract fun getName(context: Context): String
}

class Work(val name: String, duration: Seconds, val options: WorkOptions) : Exercise(duration) {
    override fun getName(context: Context) = name
}

abstract class Relaxation(private val nameStringId: Int, duration: Seconds) : Exercise(duration) {
    override fun getName(context: Context) = context.getString(nameStringId)
}



class RunUp(duration: Seconds) : Relaxation(R.string.exercises_run_up, duration)

class RestBetweenSets(duration: Seconds) : Relaxation(R.string.exercises_rest_between_sets, duration)

class CalmDown(duration: Seconds) : Relaxation(R.string.exercises_calm_down, duration)



sealed class WorkOptions

class SimpleWorkOptions : WorkOptions()

data class WorkWithAccelerationOptions(val accelerationDuration: Seconds) : WorkOptions()

data class WorkWithIntervalsOptions(val interval: Seconds, val rattle: Seconds)
