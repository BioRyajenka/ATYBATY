package com.atybaty.timer.view.workout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.atybaty.timer.R
import com.atybaty.timer.contract.WorkoutContract
import com.atybaty.timer.model.ExerciseGroup
import com.atybaty.timer.model.Workout
import com.atybaty.timer.presenter.WorkoutPresenter
import kotlinx.android.synthetic.main.fragment_workout.*

class WorkoutFragment : Fragment(), WorkoutContract.View {
    private lateinit var workout: Workout

    private lateinit var presenter: WorkoutContract.Presenter
    private lateinit var exerciseGroupAdapter: ExerciseGroupAdapter

    override fun setWorkout(workout: Workout) {
        this.workout = workout
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_workout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = WorkoutPresenter(this, context!!)
        exerciseGroupAdapter = ExerciseGroupAdapter(presenter)
        exerciseGroupAdapter.setContext(context!!)

        rv_start_sets.adapter = exerciseGroupAdapter
        rv_start_sets.layoutManager = LinearLayoutManager(context)
        presenter.fragmentViewCreated(workout)
    }

    override fun showWorkout(workout: Workout) {
        et_start_starttime_count.setText(workout.warmUp.toString())
        et_start_endtime_count.setText(workout.coolDown.toString())
        exerciseGroupAdapter.setWorkout(workout)
        exerciseGroupAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.fragmentViewDestroyed()
    }

    override fun showExerciseGroup(exerciseGroup: ExerciseGroup) {
        // TODO("next screen")
    }
}