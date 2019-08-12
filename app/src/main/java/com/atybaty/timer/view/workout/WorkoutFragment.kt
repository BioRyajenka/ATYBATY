package com.atybaty.timer.view.workout

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.atybaty.timer.view.workoutsettings.exercisegroup.ExerciseGroupFragment
import kotlinx.android.synthetic.main.fragment_workout.*

class WorkoutFragment : Fragment(), WorkoutContract.View {
    private lateinit var workout: Workout

    private lateinit var presenter: WorkoutContract.Presenter
    private lateinit var exerciseGroupAdapter: ExerciseGroupAdapter

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
        presenter.fragmentViewCreated()

        tv_start_set_add.setOnClickListener { presenter.addExerciseGroupButtonClicked() }

        iv_start_starttime_add.setOnClickListener {
            workout.warmUp++
            et_start_starttime_count.setText(workout.warmUp.toString())
            presenter.warmUpDurationSet(workout.warmUp)
        }
        iv_start_starttime_minus.setOnClickListener {
            workout.warmUp--
            et_start_starttime_count.setText(workout.warmUp.toString())
            presenter.warmUpDurationSet(workout.warmUp)
        }
        iv_start_endtime_add.setOnClickListener {
            workout.coolDown++
            et_start_endtime_count.setText( workout.coolDown.toString())
            presenter.warmUpDurationSet( workout.coolDown)
        }
        iv_start_endtime_minus.setOnClickListener {
            workout.coolDown--
            et_start_endtime_count.setText(workout.coolDown.toString())
            presenter.warmUpDurationSet( workout.coolDown)
        }

        et_start_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                workout.name = p0.toString()
            }
        })

        iv_start_save.setOnClickListener {
            presenter.saveButtonClicked()
            returnToPreviousActivity()
        }

        iv_start_back.setOnClickListener {
            returnToPreviousActivity()
        }

    }

    override fun showWorkout(workout: Workout) {
        this.workout = workout
        et_start_starttime_count.setText(workout.warmUp.toString())
        et_start_endtime_count.setText(workout.coolDown.toString())
        et_start_name.setText(workout.name)
        exerciseGroupAdapter.setWorkout(workout)
        exerciseGroupAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.fragmentViewDestroyed()
    }

    fun returnToPreviousActivity() {
        activity!!.onBackPressed()
    }

    override fun showExerciseGroup(exerciseGroup: ExerciseGroup) {
        activity!!.supportFragmentManager!!.beginTransaction().replace(R.id.fl_train_frames, ExerciseGroupFragment())
            .addToBackStack(null).commit()
    }
}