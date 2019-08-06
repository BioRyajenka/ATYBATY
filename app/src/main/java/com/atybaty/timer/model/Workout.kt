package com.atybaty.timer.model

import org.dizitart.no2.IndexType
import org.dizitart.no2.objects.Id
import org.dizitart.no2.objects.Index
import org.dizitart.no2.objects.Indices
import java.time.Duration

@Indices(Index(value = "id", type = IndexType.Unique))
data class Workout(val id: Int, val name: String, val warmUp: Duration?,
                   val exerciseGroups: List<ExerciseGroup>,
                   val coolDown: Duration?)
