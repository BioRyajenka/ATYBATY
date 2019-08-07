package com.atybaty.timer.model

import android.content.Context
import com.atybaty.timer.R
import com.atybaty.timer.utils.Seconds
import java.time.Duration

sealed class Exercise {
    abstract fun getName(context: Context): String
}

data class Work(val name: String, val options: WorkOptions) : Exercise() {
    override fun getName(context: Context) = name
}

abstract class Relaxation(private val nameStringId: Int) : Exercise() {
    abstract val duration: Seconds

    override fun getName(context: Context) = context.getString(nameStringId)
}



data class RunUp(override val duration: Seconds) : Relaxation(R.string.exercises_run_up)

data class RestBetweenSets(override val duration: Seconds) : Relaxation(R.string.exercises_rest_between_sets)

data class CalmDown(override val duration: Seconds) : Relaxation(R.string.exercises_calm_down)



sealed class WorkOptions

data class SimpleWorkOptions(val duration: Seconds) : WorkOptions()

data class WorkWithAccelerationOptions(val accelerationDuration: Seconds) : WorkOptions()

data class WorkWithIntervalsOptions(val interval: Seconds, val rattle: Seconds)

fun getExerciseDuration(exercise: Exercise): Seconds? {
    return when (exercise) {
        is Relaxation -> exercise.duration
        is Work -> {
            when (exercise.options) {
                is SimpleWorkOptions -> exercise.options.duration
                else -> null
            }
        }
    }
}