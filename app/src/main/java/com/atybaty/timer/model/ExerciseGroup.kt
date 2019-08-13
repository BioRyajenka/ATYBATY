package com.atybaty.timer.model

import com.atybaty.timer.utils.Seconds

data class ExerciseGroup(
    val name: String,
    var warmUp: Seconds,
    var defaultTime: Seconds,
    var relaxTime: Seconds,
    var repeatsCount: Int,
    val exercises: MutableList<Exercise>
) {
    constructor(name: String, exercises: MutableList<Exercise>) : this(name, 10, 60, 10, 1, exercises)
}
