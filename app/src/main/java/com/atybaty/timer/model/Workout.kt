package com.atybaty.timer.model

import java.time.Duration

data class Workout(val warmUp: Duration,
                   val exerciseGroups: List<ExerciseGroup>,
                   val coolDown: Duration)
