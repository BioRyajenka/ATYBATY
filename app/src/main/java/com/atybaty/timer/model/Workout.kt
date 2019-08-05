package com.atybaty.timer.model

import java.time.Duration

data class Workout(val warmUp: Duration,
                   val sets: List<Set>,
                   val coolDown: Duration)
