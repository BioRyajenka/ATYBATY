package com.atybaty.timer.model


data class Workout(val id: Int, var name: String,
                   val exerciseGroups: MutableList<ExerciseGroup>) {

    fun deepCopy(): Workout{
        val copyExerciseGroups = exerciseGroups.map(ExerciseGroup::deepCopy).toMutableList()
        return Workout(id, name, copyExerciseGroups)
    }
}
