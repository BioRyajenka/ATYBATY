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

    fun deepCopy(): ExerciseGroup {
        val copyExercises = exercises.map(Exercise::deepCopy).toMutableList()
        return ExerciseGroup(name, warmUp, defaultTime, relaxTime, repeatsCount, copyExercises, relaxAfter.deepCopy())
    }
}
