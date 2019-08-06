package com.atybaty.timer.model

import com.atybaty.timer.utils.Seconds
import java.time.Duration

data class Workout(val warmUp: Seconds,
                   val exerciseGroups: List<ExerciseGroup>,
                   val coolDown: Seconds)