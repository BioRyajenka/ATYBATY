package com.atybaty.timer.model

import com.atybaty.timer.util.Seconds
import org.dizitart.no2.objects.Id

data class Workout(val id: Int, var name: String,
                   val exerciseGroups: MutableList<ExerciseGroup>)
