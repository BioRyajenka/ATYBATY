package com.atybaty.timer.view.workoutlist

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.atybaty.timer.dataholders.CurrentWorkoutHolder
import com.atybaty.timer.R
import com.atybaty.timer.contract.WorkoutListContract
import com.atybaty.timer.model.Workout
import com.atybaty.timer.presenter.WorkoutListPresenter
import com.atybaty.timer.view.WorkoutSettingsActivity
import kotlinx.android.synthetic.main.activity_workout_list.*

class WorkoutListActivity : AppCompatActivity(), WorkoutListContract.View {
    private lateinit var presenter: WorkoutListContract.Presenter
    private lateinit var workoutAdapter: WorkoutAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_list)

        presenter = WorkoutListPresenter(this, this)

        workoutAdapter = WorkoutAdapter(this, presenter)
        rv_main_trains.layoutManager = LinearLayoutManager(this)
        rv_main_trains.adapter = workoutAdapter

        iv_main_add.setOnClickListener { presenter.addButtonClicked() }

        presenter.activityCreated()
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
        CurrentWorkoutHolder.currentWorkout = workout
        startActivity(Intent(this, WorkoutSettingsActivity::class.java))
    }

    override fun showTimer(workout: Workout) {
        Toast.makeText(applicationContext, "TODO", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.activityDestroyed()
    }
}
