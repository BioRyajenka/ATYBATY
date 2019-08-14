package com.atybaty.timer.model


data class Workout(val id: Int, var name: String,
                   val exerciseGroups: MutableList<ExerciseGroup>)
