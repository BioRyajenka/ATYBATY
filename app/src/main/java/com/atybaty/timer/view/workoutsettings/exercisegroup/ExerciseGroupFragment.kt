package com.atybaty.timer.view.workoutsettings.exercisegroup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.atybaty.timer.R
import com.atybaty.timer.contract.ExerciseGroupContract
import com.atybaty.timer.model.Exercise
import com.atybaty.timer.model.ExerciseGroup
import com.atybaty.timer.presenter.ExerciseGroupPresenter
import com.atybaty.timer.view.workoutsettings.exercisesettings.ExerciseSettingsDialog
import kotlinx.android.synthetic.main.fragment_set.*

class ExerciseGroupFragment : Fragment(), ExerciseGroupContract.View {
    private val presenter = ExerciseGroupPresenter(this)
    private val exerciseAdapter = ExerciseAdapter(presenter)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_set, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        exerciseAdapter.setContext(context!!)
        rv_set_exercise.adapter = exerciseAdapter
        rv_set_exercise.layoutManager = LinearLayoutManager(context)

        tv_set_default_btn.setOnClickListener { presenter.setUpDefaultButtonClicked() }
        iv_set_save.setOnClickListener { presenter.saveButtonClicked() }
        iv_set_back.setOnClickListener { presenter.backButtonClicked() }

        presenter.fragmentViewCreated(context!!)
    }

    override fun showExerciseGroup(exerciseGroup: ExerciseGroup) {
        tv_set_title.text = exerciseGroup.name
        exerciseAdapter.setExercises(exerciseGroup.exercises)
        exerciseAdapter.notifyDataSetChanged()
    }

    override fun updateExercise(exerciseItemPosition: Int, exerciseGroup: ExerciseGroup) {
        exerciseAdapter.setExercises(exerciseGroup.exercises)
        exerciseAdapter.notifyItemChanged(exerciseItemPosition)
    }

    override fun showExerciseSettings(exerciseItemPosition: Int, exercise: Exercise) {
        ExerciseSettingsDialog {
            presenter.exerciseUpdated(exerciseItemPosition, exercise)
        }.show(fragmentManager, "dialog")
    }

    override fun returnToPreviousFragment() {
        fragmentManager!!.popBackStack()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.fragmentDestroyed()
    }
}
