package com.atybaty.timer.contract

import com.atybaty.timer.model.ExerciseGroup
import com.atybaty.timer.model.Workout
import com.atybaty.timer.util.Seconds

interface WorkoutContract {

    interface Presenter {
        fun fragmentViewCreated()
        fun saveButtonClicked()

        fun exerciseGroupClicked(itemPosition: Int)
        fun timeAfterExererciseSet(itemPosition: Int, time: Seconds)
        fun deleteButtonClicked(itemPosition: Int)
        fun addExerciseGroupButtonClicked()
    }

    interface View {
        fun showWorkout(workout: Workout)
        fun showExerciseGroup(exerciseGroup: ExerciseGroup)
        fun returnToPreviousScreen()
    }
}
