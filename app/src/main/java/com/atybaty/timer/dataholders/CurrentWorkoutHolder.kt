package com.atybaty.timer.dataholders

import com.atybaty.timer.model.Workout

class CurrentWorkoutHolder private constructor() {
    companion object {
        lateinit var currentWorkout: Workout
        var currentExerciseGroupPosition = 0
        var currentWorkPosition = 0
    }
}
