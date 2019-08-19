package com.atybaty.timer.view.workoutsettings.workout

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
import com.atybaty.timer.view.workoutsettings.exercisegroupsettings.ExerciseGroupSettingsFragment
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

        iv_start_set_add.setOnClickListener { presenter.addExerciseGroupButtonClicked() }

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
            returnToPreviousScreen()
        }

        iv_start_back.setOnClickListener {
            returnToPreviousScreen()
        }

    }

    override fun showWorkout(workout: Workout) {
        this.workout = workout
        et_start_name.setText(workout.name)
        exerciseGroupAdapter.setWorkout(workout)
        exerciseGroupAdapter.notifyDataSetChanged()
    }

    override fun returnToPreviousScreen() {
        activity!!.onBackPressed()
    }

    override fun showExerciseGroup(exerciseGroup: ExerciseGroup) {
        activity!!.supportFragmentManager!!
            .beginTransaction()
            .replace(R.id.fl_train_frames, ExerciseGroupSettingsFragment())
            .addToBackStack(null)
            .commit()
    }
}