package com.atybaty.timer.dataholders

import com.atybaty.timer.model.ExerciseGroup
import com.atybaty.timer.model.Work
import com.atybaty.timer.model.Workout

class CurrentWorkoutHolder private constructor() {
    companion object {
        lateinit var currentWorkout: Workout
        lateinit var currentExerciseGroup: ExerciseGroup
        lateinit var currentWork: Work
    }
}
