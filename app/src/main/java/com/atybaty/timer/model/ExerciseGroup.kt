package com.atybaty.timer.model

import com.atybaty.timer.util.Seconds

data class ExerciseGroup(
    val name: String,
    var runUpDuration: Seconds,
    var defaultWorkDuration: Seconds,
    var relaxDuration: Seconds,
    var repeatsCount: Int,
    val exercises: MutableList<Exercise>,
    var calmDown: CalmDown
) {
    constructor(name: String, exercises: MutableList<Exercise>) : this(name, 10, 60, 10, 1, exercises, CalmDown(10))

    val extendedExercises: List<Exercise>
        get() = listOf(RunUp(runUpDuration)) + exercises + calmDown

//    fun setExercise(index: Int, exercise: Exercise) {
//        when (index) {
//            0 -> runUpDuration = exercise.duration
//            rawExercises.size + 1 -> calmDown = exercise as CalmDown
//            else -> rawExercises[index - 1] = exercise
//        }
//    }

    fun deepCopy(): ExerciseGroup {
        val copyExercises = exercises.map(Exercise::deepCopy).toMutableList()
        return ExerciseGroup(name, runUpDuration, defaultWorkDuration, relaxDuration, repeatsCount, copyExercises, calmDown.deepCopy())
    }
}
