package com.atybaty.timer.contract

import com.atybaty.timer.model.ExerciseGroup
import com.atybaty.timer.model.Workout
import com.atybaty.timer.utils.Seconds

interface WorkoutContract {

    interface Presenter {
        fun fragmentViewCreated()
        fun saveButtonClicked()

        fun warmUpDurationSet(duration: Seconds)
        fun coolDownDurationSet(duration: Seconds)

        fun exerciseGroupClicked(itemPosition: Int)

        fun deleteButtonClicked(itemPosition: Int)
        fun addExerciseGroupButtonClicked()
    }

    interface View {
        fun showWorkout(workout: Workout)
        fun showExerciseGroup(exerciseGroup: ExerciseGroup)
        fun returnToPreviousScreen()
    }
}
