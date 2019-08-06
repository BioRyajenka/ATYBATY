package com.atybaty.timer.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.atybaty.timer.R
import com.atybaty.timer.presenter.WorkoutListPresenter

class WorkoutListActivity : AppCompatActivity() {
    private lateinit var presenter: WorkoutListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_list)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}