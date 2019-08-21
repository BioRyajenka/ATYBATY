package com.atybaty.timer.model

import com.atybaty.timer.util.Seconds

data class ExerciseGroup(
    val name: String,
    var runUpDuration: Seconds,
    var defaultWorkDuration: Seconds,
    var relaxDuration: Seconds,
    var repeatsCount: Int,
    val exercises: MutableList<Exercise>,
    var restAfterSet: RestBetweenSets
) {
    constructor(name: String, exercises: MutableList<Exercise>) : this(name, 10, 60, 10, 1, exercises, RestBetweenSets(10))

    val extendedExercises: List<Exercise>
        get() = listOf(RunUp(runUpDuration)) + exercises + restAfterSet

    fun deepCopy(): ExerciseGroup {
        val copyExercises = exercises.map(Exercise::deepCopy).toMutableList()
        return ExerciseGroup(name, runUpDuration, defaultWorkDuration, relaxDuration, repeatsCount, copyExercises, restAfterSet.deepCopy())
    }
}
