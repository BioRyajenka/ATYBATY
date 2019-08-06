package com.atybaty.timer.contract

import com.atybaty.timer.model.Workout

interface IWorkoutListContract {

    interface Presenter{
        fun activityCreated()
        fun addButtonClicked()
        fun deleteButtonClicked(itemPosition: Int)
        fun playButtonClicked(itemPosition: Int)
        fun itemClicked(itemPosition: Int)
        fun activityDestroied()
    }

    interface View{
        fun showWorkoutList(workouts: ArrayList<Workout>)
        fun showEmptyMessage()
        fun showEmptyWorkout()
        fun showWorkout(workoutId: Long)
        fun showTimer(workoutId: Long)
    }
}
