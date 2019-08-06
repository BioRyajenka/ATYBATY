package com.atybaty.timer.model

import com.atybaty.timer.utils.Seconds
import org.dizitart.no2.IndexType
import org.dizitart.no2.objects.Id
import org.dizitart.no2.objects.Index
import org.dizitart.no2.objects.Indices


@Indices(Index(value = "id", type = IndexType.Unique))
data class Workout(val id: Int, var name: String, var warmUp: Seconds,
                   val exerciseGroups: List<ExerciseGroup>,
                   var coolDown: Seconds)