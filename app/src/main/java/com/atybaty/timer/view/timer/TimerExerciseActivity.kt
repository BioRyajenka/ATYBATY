package com.atybaty.timer.view.timer

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.atybaty.timer.R
import com.atybaty.timer.contract.TimerContract
import com.atybaty.timer.contract.TimerContract.Presenter.LockStatus
import com.atybaty.timer.contract.TimerContract.Presenter.PauseStatus
import com.atybaty.timer.model.*
import com.atybaty.timer.presenter.TimerExercisePresenter
import com.atybaty.timer.util.Seconds
import kotlinx.android.synthetic.main.activity_timer.*

class TimerExerciseActivity : AppCompatActivity(), TimerContract.View {
    private lateinit var timerPresenter: TimerContract.Presenter
    private lateinit var timerExerciseAdapter: TimerExerciseAdapter

    private lateinit var workout: Workout
    private var currentScreenColorId: Int = -1 // uninitialized

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        timerPresenter = TimerExercisePresenter(this)

        timerExerciseAdapter = TimerExerciseAdapter(this, timerPresenter)
        rv_timer_workout.layoutManager = LinearLayoutManager(this)
        rv_timer_workout.adapter = timerExerciseAdapter

        iv_timer_back.setOnClickListener { timerPresenter.backButtonClicked() }
        iv_timer_lock.setOnClickListener { timerPresenter.lockButtonClicked() }
        tv_timer_pause.setOnClickListener { timerPresenter.pauseButtonClicked() }

        timerPresenter.activityCreated(this)
    }

    override fun onStop() {
        super.onStop()
        timerPresenter.activityStopped()
    }

    override fun showPreviousScreen() {
        onBackPressed()
    }

    override fun showWorkout(workout: Workout) {
        this.workout = workout
        timerExerciseAdapter.setWorkout(workout)
        timerExerciseAdapter.notifyDataSetChanged()
    }

    override fun showNotificationAboutLock() {
        Toast.makeText(this, getString(R.string.timer_notification_about_lock), Toast.LENGTH_SHORT).show()
    }

    override fun updateTime(time: Seconds) {
        tv_timer_time.text = time.toString()
    }

    override fun updateAdditionalInfo(text: String, colorId: Int) {
        tv_timer_additional_info.text = text
        tv_timer_additional_info.setTextColor(resources.getColor(colorId))
    }

    override fun updatePauseButton(status: PauseStatus) {
        tv_timer_pause.text = when (status) {
            PauseStatus.PLAYING -> "Остановить"
            PauseStatus.PAUSED -> "Продолжить"
            PauseStatus.NOT_STARTED -> "Старт"
        }
    }

    override fun updateLockButton(status: LockStatus) {
        val imageResource = if (status == LockStatus.LOCK) {
            R.drawable.ic_lock_white
        } else {
            R.drawable.ic_lock_open_white
        }
        iv_timer_lock.setImageResource(imageResource)
    }

    override fun updateCurrentExerciseSelection(exerciseGroupIndex: Int, exerciseIndex: Int) {
        timerExerciseAdapter.setSelectedExercise(exerciseGroupIndex, exerciseIndex)
        timerExerciseAdapter.notifyDataSetChanged()
        val exerciseGroup = workout.exerciseGroups[exerciseGroupIndex]
        val exercise = exerciseGroup.extendedExercises[exerciseIndex]

        tv_timer_exercise_group_name.text =
            "Сет: ${exerciseGroupIndex + 1}/${workout.exerciseGroups.size} (${exerciseGroup.name})"
        tv_timer_title.text = if (exercise is Work) {
            when (exercise.options) {
                is SimpleWorkOptions -> "Работа"
                is WorkWithIntervalsOptions -> "Работа с интервалами"
                is WorkWithAccelerationOptions -> "Работа с ускорениями"
            }
        } else {
            "Отдых"
        }
    }

    override fun clearCurrentExerciseSelection() {
        tv_timer_exercise_group_name.text = ""
        timerExerciseAdapter.clearExerciseSelection()
        timerExerciseAdapter.notifyDataSetChanged()
    }

    override fun updateScreenColor(colorId: Int) {
        if (colorId != currentScreenColorId) {
            currentScreenColorId = colorId
            val color = resources.getColor(colorId)
            ll_timer_background.setBackgroundColor(color)
            timerExerciseAdapter.setItemsColor(colorId)

            val recyclerViewState = rv_timer_workout.layoutManager!!.onSaveInstanceState()
            rv_timer_workout.adapter = timerExerciseAdapter
            rv_timer_workout.layoutManager!!.onRestoreInstanceState(recyclerViewState)
        }
    }
}
