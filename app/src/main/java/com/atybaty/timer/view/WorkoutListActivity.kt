package com.atybaty.timer.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.atybaty.timer.R
import com.atybaty.timer.contract.WorkoutListContract
import com.atybaty.timer.model.Workout
import com.atybaty.timer.presenter.WorkoutListPresenter
import com.atybaty.timer.view.adapters.WorkoutAdapter
import kotlinx.android.synthetic.main.activity_workout_list.*

class WorkoutListActivity : AppCompatActivity(), WorkoutListContract.View {
    private lateinit var presenter: WorkoutListContract.Presenter
    private lateinit var workoutAdapter: WorkoutAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_list)

        presenter = WorkoutListPresenter(this)
        workoutAdapter = WorkoutAdapter(this, presenter)

        iv_main_add.setOnClickListener { presenter.addButtonClicked() }

        presenter.activityCreated()
    }

    override fun showWorkoutsList(workouts: List<Workout>) {
        tv_main_message.visibility = View.GONE
        rv_main_trains.visibility = View.VISIBLE
        workoutAdapter.setWorkouts(workouts)
        rv_main_trains.layoutManager = LinearLayoutManager(this)
        rv_main_trains.adapter = workoutAdapter
        workoutAdapter.notifyDataSetChanged()
    }

    override fun showEmptyMessage() {
        println("Showing empty message")
        rv_main_trains.visibility = View.GONE
        tv_main_message.visibility = View.VISIBLE
    }

    override fun showNewWorkout(workout: Workout) {
        Toast.makeText(applicationContext, "TODO", Toast.LENGTH_SHORT).show()
    }

    override fun showWorkout(workoutId: Long) {
        Toast.makeText(applicationContext, "TODO", Toast.LENGTH_SHORT).show()
    }

    override fun showTimer(workoutId: Long) {
        Toast.makeText(applicationContext, "TODO", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.activityDestroyed()
    }
}
