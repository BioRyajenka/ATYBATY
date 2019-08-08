package com.atybaty.timer.contract

import com.atybaty.timer.model.Workout

interface WorkoutListContract {

    interface Presenter {
        fun activityCreated()
        fun activityDestroyed()

        fun addButtonClicked()
        fun deleteButtonClicked(itemPosition: Int)
        fun playButtonClicked(itemPosition: Int)
        fun itemClicked(itemPosition: Int)
    }

    interface View {
        fun showWorkoutsList(workouts: List<Workout>)
        fun showEmptyMessage()
        fun showWorkout(workout: Workout)
        fun showTimer(workout: Workout)
    }
}
