package com.atybaty.timer.presenter

import com.atybaty.timer.contract.WorkoutContract

package com.atybaty.timer.presenter

import android.widget.Toast
import com.atybaty.timer.R
import com.atybaty.timer.WorkoutRepositoryHolder
import com.atybaty.timer.view.WorkoutFragment

class WorkoutPresenter(private val view: WorkoutFragment) : WorkoutContract.Presenter {
    private val workoutRepository = WorkoutRepositoryHolder.getWorkoutRepository(view)
    private val workouts = workoutRepository.getAllWorkouts().toMutableList()

    private fun viewShowWorkouts() {
        if (workouts.isEmpty()) {
            view.showEmptyMessage()
        } else {
            view.showWorkoutsList(workouts)
        }
    }

    override fun fragmentViewCreated() {
        viewShowWorkouts()
    }

    override fun addButtonClicked() {
        val newWorkout = workoutRepository.createNewWorkout(
            name = view.getString(R.string.default_workout_name),
            warmUp = view.resources.getInteger(R.integer.default_warmup_time_in_seconds),
            exerciseGroups = emptyList(),
            coolDown = view.resources.getInteger(R.integer.default_cooldown_time_in_seconds)
        )
        view.showNewWorkout(newWorkout)

        // this is stub
        workouts.add(newWorkout)
        viewShowWorkouts()
    }

    override fun deleteButtonClicked(itemPosition: Int) {
        val deletedWorkout = workouts.removeAt(itemPosition)
        workoutRepository.deleteWorkoutById(deletedWorkout.id)
        viewShowWorkouts()
    }

    override fun playButtonClicked(itemPosition: Int) {
        Toast.makeText(view, "TODO", Toast.LENGTH_SHORT).show()
    }

    override fun itemClicked(itemPosition: Int) {
        Toast.makeText(view, "TODO", Toast.LENGTH_SHORT).show()
    }

    override fun activityDestroyed() {
        // maybe save to db, but not sure
    }
}
