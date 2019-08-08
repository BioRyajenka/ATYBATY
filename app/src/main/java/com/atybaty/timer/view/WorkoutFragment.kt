package com.atybaty.timer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.atybaty.timer.R
import com.atybaty.timer.contract.WorkoutContract
import com.atybaty.timer.contract.WorkoutListContract
import com.atybaty.timer.model.ExerciseGroup
import com.atybaty.timer.model.Workout
import com.atybaty.timer.presenter.WorkoutListPresenter
import com.atybaty.timer.presenter.WorkoutPresenter
import com.atybaty.timer.view.adapters.ExerciseGroupAdapter
import com.atybaty.timer.view.adapters.WorkoutAdapter
import kotlinx.android.synthetic.main.activity_workout_list.*
import kotlinx.android.synthetic.main.fragment_workout.*

class WorkoutFragment : Fragment(), WorkoutContract.View {
    private lateinit var workout: Workout

    private lateinit var presenter: WorkoutContract.Presenter
    private lateinit var workoutAdapter: ExerciseGroupAdapter

    fun setWorkout(workout: Workout) {
        this.workout = workout
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        presenter = WorkoutPresenter(this, context!!)
        workoutAdapter = ExerciseGroupAdapter(presenter)
        workoutAdapter.setContext(context!!)

        return inflater.inflate(R.layout.fragment_set, container, false).apply {
            rv_start_sets.adapter = workoutAdapter
            rv_start_sets.layoutManager = LinearLayoutManager(context)
        }.also {
            presenter.fragmentViewCreated(workout)
        }
    }

    override fun showWorkout(workout: Workout) {
        et_start_starttime_count.setText(workout.warmUp.toString())
        et_start_endtime_count.setText(workout.coolDown.toString())
        workoutAdapter.setWorkout(workout)
        workoutAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.fragmentViewDestroyed()
    }

    override fun showExerciseGroup(exerciseGroup: ExerciseGroup) {
        // TODO("next screen")
    }
}