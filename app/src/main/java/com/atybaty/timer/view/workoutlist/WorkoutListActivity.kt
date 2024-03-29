package com.atybaty.timer.view.workoutlist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.atybaty.timer.R
import com.atybaty.timer.contract.WorkoutListContract
import com.atybaty.timer.model.Workout
import com.atybaty.timer.presenter.WorkoutListPresenter
import com.atybaty.timer.view.timer.TimerExerciseActivity
import com.atybaty.timer.view.workoutsettings.WorkoutSettingsActivity
import kotlinx.android.synthetic.main.activity_workout_list.*

class WorkoutListActivity : AppCompatActivity(), WorkoutListContract.View {
    private lateinit var presenter: WorkoutListContract.Presenter
    private lateinit var workoutAdapter: WorkoutAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_list)

        presenter = WorkoutListPresenter(this)

        workoutAdapter = WorkoutAdapter(this, presenter)
        rv_main_trains.layoutManager = LinearLayoutManager(this)
        rv_main_trains.adapter = workoutAdapter

        iv_main_add.setOnClickListener { presenter.addButtonClicked() }

        presenter.activityResumed(this)
    }

    override fun showWorkoutsList(workouts: List<Workout>) {
        tv_main_message.visibility = View.GONE
        rv_main_trains.visibility = View.VISIBLE
        workoutAdapter.setWorkouts(workouts)
        workoutAdapter.notifyDataSetChanged()
    }

    override fun showEmptyMessage() {
        rv_main_trains.visibility = View.GONE
        tv_main_message.visibility = View.VISIBLE
    }


    override fun showWorkout(workout: Workout) {
        startActivity(Intent(this, WorkoutSettingsActivity::class.java))
    }

    override fun showTimer(workout: Workout) {
        startActivity(Intent(this, TimerExerciseActivity::class.java))
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.activityDestroyed()
    }

    override fun onResume() {
        super.onResume()
        presenter.activityResumed(this)
    }
}
