package com.atybaty.timer.model

import android.content.Context
import com.atybaty.timer.R
import com.atybaty.timer.util.Seconds
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
sealed class Exercise(var duration: Seconds) {
    abstract fun getName(context: Context): String
}

class Work(var name: String, duration: Seconds, var options: WorkOptions) : Exercise(duration) {
    override fun getName(context: Context) = name

    fun copy(name: String = this.name, duration: Seconds = this.duration, options: WorkOptions = this.options): Work {
        return Work(name, duration, options)
    }
}

abstract class Relaxation(private val nameStringId: Int, duration: Seconds) : Exercise(duration) {
    override fun getName(context: Context) = context.getString(nameStringId)
}


class RunUp(duration: Seconds) : Relaxation(R.string.exercises_run_up, duration)

class RestBetweenSets(duration: Seconds) : Relaxation(R.string.exercises_rest_between_sets, duration)

class CalmDown(duration: Seconds) : Relaxation(R.string.exercises_calm_down, duration)


@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
sealed class WorkOptions

object SimpleWorkOptions : WorkOptions()

data class WorkWithAccelerationOptions(var accelerationDuration: Seconds) : WorkOptions()

data class WorkWithIntervalsOptions(var interval: Seconds, var rattle: Seconds) : WorkOptions()
