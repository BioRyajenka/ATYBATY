package com.atybaty.timer.contract

import com.atybaty.timer.model.Workout
import org.dizitart.no2.Nitrite

interface IWorkoutListContract {

    interface Presenter {
        fun activityCreated(dbInstance: Nitrite)
        fun activityDestroyed()

        fun addButtonClicked()
        fun deleteButtonClicked(itemPosition: Int)
        fun playButtonClicked(itemPosition: Int)
        fun itemClicked(itemPosition: Int)
    }

    interface View {
        fun showWorkoutList(workouts: ArrayList<Workout>)
        fun showEmptyMessage()
        fun showEmptyWorkout()
        fun showWorkout(workoutId: Long)
        fun showTimer(workoutId: Long)
    }
}
