package com.atybaty.timer.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.atybaty.timer.R
import com.atybaty.timer.contract.IWorkoutListContract
import com.atybaty.timer.model.Workout
import com.atybaty.timer.presenter.WorkoutListPresenter
import com.atybaty.timer.view.adapters.WorkoutAdapter
import kotlinx.android.synthetic.main.activity_workout_list.*

class WorkoutListActivity : AppCompatActivity(), IWorkoutListContract.View {
    private lateinit var presenter: IWorkoutListContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_list)

        //presenter = WorkoutListPresenter(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.activityDestroied()
    }

    override fun showWorkoutList(workouts: ArrayList<Workout>) {
        tv_main_message.visibility = View.GONE
        rv_main_trains.visibility = View.VISIBLE
        val adapter = WorkoutAdapter(this, presenter)
        adapter.setWorkouts(workouts)
        rv_main_trains.layoutManager = LinearLayoutManager(this)
        rv_main_trains.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun showEmptyMessage() {
        rv_main_trains.visibility = View.GONE
        tv_main_message.visibility = View.VISIBLE
    }

    override fun showEmptyWorkout() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showWorkout(workoutId: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showTimer(workoutId: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
