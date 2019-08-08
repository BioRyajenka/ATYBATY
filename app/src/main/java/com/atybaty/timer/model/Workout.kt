package com.atybaty.timer.model

import com.atybaty.timer.utils.Seconds
import org.dizitart.no2.objects.Id


data class Workout(@Id val id: Int, var name: String, var warmUp: Seconds,
                   val exerciseGroups: MutableList<ExerciseGroup>,
                   var coolDown: Seconds)
