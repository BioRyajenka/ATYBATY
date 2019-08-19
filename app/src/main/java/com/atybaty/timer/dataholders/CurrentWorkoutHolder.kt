package com.atybaty.timer.dataholders

import com.atybaty.timer.model.Workout

class CurrentWorkoutHolder private constructor() {
    companion object {
        lateinit var currentWorkout: Workout
        var currenExerciseGroupPosition = 0
        var currentWorkPosition = 0
    }
}
