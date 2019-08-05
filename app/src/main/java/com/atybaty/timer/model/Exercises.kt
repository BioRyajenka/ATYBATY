package com.atybaty.timer.model

import com.atybaty.timer.R
import java.time.Duration

sealed class Exercise

sealed class WorkOptions

data class SimpleWorkOptions(val duration: Duration) : WorkOptions()

data class WorkWithAccelerationOptions(val accelerationDuration: Duration) : WorkOptions()

data class WorkWithIntervalsOptions(val interval: Duration, val rattle: Duration)

data class Work(val name: String, val options: WorkOptions) : Exercise()

abstract class Relaxation(val nameStringId: Int) : Exercise() {
    abstract val duration: Duration
}

data class RunUp(override val duration: Duration) : Relaxation(R.string.exercises_run_up)

data class RestBetweenSets(override val duration: Duration) : Relaxation(R.string.exercises_rest_between_sets)

data class CalmDown(override val duration: Duration) : Relaxation(R.string.exercises_calm_down)
