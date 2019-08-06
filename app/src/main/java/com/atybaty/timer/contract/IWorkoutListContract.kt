package com.atybaty.timer.contract

import com.atybaty.timer.model.Workout

interface IWorkoutListContract {

    interface Presenter{
        fun onCreated()
        fun addWorkout()
        fun deleteWorkout(workoutId: Long)
        fun getWorkouts(): ArrayList<Workout>
        fun playWorkout(workoutId: Long)
        fun changeWorkout(workoutId: Long)
    }

    interface View{
        fun showWorkouts()
        fun showEmptyMessage()
        fun showWorkout()
        fun showWorkout(workoutId: Long)
        fun showTimer(workoutId: Long)
    }
}
