package com.atybaty.timer.model

import com.atybaty.timer.utils.Seconds


data class Workout(val id: Int, var name: String, var warmUp: Seconds,
                   val exerciseGroups: MutableList<ExerciseGroup>,
                   var coolDown: Seconds)