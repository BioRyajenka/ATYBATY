package com.atybaty.timer.contract

import com.atybaty.timer.model.ExerciseGroup
import com.atybaty.timer.model.Workout
import com.atybaty.timer.utils.Seconds

interface ExerciseGroupContract {

    interface Presenter {
        fun fragmentViewCreated()
        fun fragmentViewDestroyed()

        fun warmUpDurationSetted(duration: Seconds)
        fun coolDownDurationSetted(duration: Seconds)

        fun exerciseGroupClicked(itemPosition: Int)
        fun addExerciseGroupButtonClicked()
    }

    interface View {
        fun showWorkout(workout: Workout)
        fun showExerciseGroup(exerciseGroup: ExerciseGroup)
    }
}
