package com.atybaty.timer.model


data class Workout(val id: Int, var name: String,
                   val exerciseGroups: MutableList<ExerciseGroup>) {

    fun deepCopy(id: Int = this.id, name: String = this.name,
                 exerciseGroups: MutableList<ExerciseGroup> = this.exerciseGroups): Workout{
        val copyExerciseGroups = ArrayList<ExerciseGroup>()
        exerciseGroups.forEach {
            copyExerciseGroups.add(it.deepCopy())
        }
        return Workout(id, name, copyExerciseGroups)
    }
}
