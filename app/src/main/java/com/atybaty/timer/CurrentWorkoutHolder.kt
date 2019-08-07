package com.atybaty.timer

import com.atybaty.timer.model.Workout

class CurrentWorkoutHolder private constructor() {
    companion object {
        lateinit var currentWorkout: Workout
    }
}