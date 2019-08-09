package com.atybaty.timer.view.timer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.atybaty.timer.R
import com.atybaty.timer.contract.TimerContract
import com.atybaty.timer.model.Workout
import kotlinx.android.synthetic.main.activity_timer.*

class TimerExerciseActivity: AppCompatActivity(), TimerContract.View {

    private lateinit var timerPresenter: TimerContract.Presenter
    private lateinit var timerExerciseAdapter: TimerExerciseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        timerPresenter.activityCreated()

        iv_timer_back.setOnClickListener { timerPresenter.backButtonClicked() }
        iv_timer_lock.setOnClickListener { timerPresenter.lockButtonClicked() }
        tv_timer_pause.setOnClickListener { timerPresenter.pauseButtonClicked() }
    }

    override fun updateTime(time: Long) {
        tv_timer_time.text = time.toString()
    }

    override fun updatePauseButton(tag: TimerContract.Presenter.PauseButtonTag) {
        if (tag == TimerContract.Presenter.PauseButtonTag.PLAY){
            tv_timer_pause.text = "Продолжить"
        }else{
            tv_timer_pause.text = "Остановить"
        }
    }

    override fun showPreviousScreen() {
        onBackPressed()
    }

    override fun showExercises(workout: Workout) {
        rv_timer_workout.layoutManager = LinearLayoutManager(this)
        timerExerciseAdapter = TimerExerciseAdapter(this, timerPresenter)
        timerExerciseAdapter.setWorkout(workout)
        rv_timer_workout.adapter = timerExerciseAdapter
        timerExerciseAdapter.notifyDataSetChanged()
    }

    override fun updateExerciseName(name: String) {
        tv_timer_title.text = name
    }

    override fun updateExerciseGroupName(name: String) {
        tv_timer_set_number.text = name
    }

    override fun updateCurrentExerciseFromList(itemPosition: Int) {
        timerExerciseAdapter.setSelectPosition(itemPosition)
    }
}