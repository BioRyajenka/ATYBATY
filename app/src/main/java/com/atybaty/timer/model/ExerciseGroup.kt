package com.atybaty.timer.model

import com.atybaty.timer.util.Seconds

data class ExerciseGroup(
    val name: String,
    var warmUp: Seconds,
    var defaultTime: Seconds,
    var relaxTime: Seconds,
    var repeatsCount: Int,
    val exercises: MutableList<Exercise>,
    val relaxAfter: CalmDown
) {
    constructor(name: String, exercises: MutableList<Exercise>) : this(name, 10, 60, 10, 1, exercises, CalmDown(10))

    fun deepCopy(
        name: String = this.name, warmUp: Seconds = this.warmUp,
        defaultTime: Seconds = this.defaultTime, relaxTime: Seconds = this.relaxTime,
        repeatsCount: Int = this.repeatsCount,
        exercises: MutableList<Exercise> = this.exercises,
        relaxAfter: CalmDown = this.relaxAfter
    ): ExerciseGroup {
        val copyExercises = ArrayList<Exercise>()
        exercises.forEach {
            if (it is Work) {
                when (it.options) {
                    is SimpleWorkOptions -> {
                        copyExercises.add(Work(it.name, it.duration, SimpleWorkOptions))
                    }
                    is WorkWithAccelerationOptions -> {
                        val duration = (it.options as WorkWithAccelerationOptions).accelerationDuration
                        copyExercises.add(Work(it.name, it.duration, WorkWithAccelerationOptions(duration)))
                    }
                    else -> {
                        val interval = (it.options as WorkWithIntervalsOptions).interval
                        val rattle = (it.options as WorkWithIntervalsOptions).rattle
                        copyExercises.add(Work(it.name, it.duration, WorkWithIntervalsOptions(interval, rattle)))
                    }
                }
            } else {
                val relaxation = it as Relaxation
                when (relaxation) {
                    is RunUp -> {
                        copyExercises.add(RunUp(relaxation.duration))
                    }
                    is RestBetweenSets -> {
                        copyExercises.add(RestBetweenSets(relaxation.duration))
                    }
                    else -> {
                        copyExercises.add(CalmDown(relaxation.duration))
                    }
                }
            }
        }
        return ExerciseGroup(name, warmUp, defaultTime, relaxTime, repeatsCount, copyExercises, relaxAfter)
    }
}
