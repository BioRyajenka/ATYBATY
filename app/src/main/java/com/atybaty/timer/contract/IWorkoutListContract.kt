package com.atybaty.timer.contract

import com.atybaty.timer.model.Workout

interface IWorkoutListContract {

    interface Presenter{
        fun activityCreated()
        fun addButtonClicked()
        fun deleteButtonClicked(itemPosition: Int)
        fun playButtonCLicked(itemPosition: Int)
        fun itemClicked(itemPosition: Int)
    }

    interface View{
        fun showWorkouts(workouts: ArrayList<Workout>)
        fun showEmptyMessage()
        fun showWorkout()
        fun showWorkout(workoutId: Long)
        fun showTimer(workoutId: Long)
    }
}
