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
import com.atybaty.timer.presenter.WorkoutListPresenter
import com.atybaty.timer.presenter.WorkoutPresenter
import com.atybaty.timer.view.adapters.WorkoutAdapter
import kotlinx.android.synthetic.main.activity_workout_list.*

class WorkoutFragment : Fragment() {
    private lateinit var presenter: WorkoutContract.Presenter
    private lateinit var workoutAdapter: WorkoutAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_set, container, false).apply {
            rv_set_exercise.adapter = exerciseGroupAdapter
            rv_set_exercise.layoutManager = LinearLayoutManager(context)
        }
    }
}