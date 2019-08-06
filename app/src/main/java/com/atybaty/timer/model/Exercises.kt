package com.atybaty.timer.model

import com.atybaty.timer.R
import com.atybaty.timer.utils.Seconds
import java.time.Duration

sealed class Exercise

data class Work(val name: String, val options: WorkOptions) : Exercise()

abstract class Relaxation(val nameStringId: Int) : Exercise() {
    abstract val duration: Seconds
}



data class RunUp(override val duration: Seconds) : Relaxation(R.string.exercises_run_up)

data class RestBetweenSets(override val duration: Seconds) : Relaxation(R.string.exercises_rest_between_sets)

data class CalmDown(override val duration: Seconds) : Relaxation(R.string.exercises_calm_down)



sealed class WorkOptions

data class SimpleWorkOptions(val duration: Seconds) : WorkOptions()

data class WorkWithAccelerationOptions(val accelerationDuration: Seconds) : WorkOptions()

data class WorkWithIntervalsOptions(val interval: Seconds, val rattle: Seconds)
