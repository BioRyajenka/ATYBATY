package com.atybaty.timer.view.workoutsettings.exercisegroup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.atybaty.timer.R
import com.atybaty.timer.contract.ExerciseGroupContract
import com.atybaty.timer.model.ExerciseGroup
import com.atybaty.timer.presenter.ExerciseGroupPresenter
import kotlinx.android.synthetic.main.fragment_set.*

class ExerciseGroupFragment : Fragment(), ExerciseGroupContract.View {
    private lateinit var exerciseGroup: ExerciseGroup

    private val presenter = ExerciseGroupPresenter(this)
    private val exerciseGroupAdapter = ExerciseGroupAdapter(presenter)

    fun setExerciseGroup(exerciseGroup: ExerciseGroup) {
        this.exerciseGroup = exerciseGroup
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_set, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        exerciseGroupAdapter.setContext(context!!)
        rv_set_exercise.adapter = exerciseGroupAdapter
        rv_set_exercise.layoutManager = LinearLayoutManager(context)

        tv_add_work.setOnClickListener { presenter.addWorkButtonClicked() }
        tv_add_rest.setOnClickListener { presenter.addRestButtonClicked() }
        iv_set_save.setOnClickListener { presenter.saveButtonClicked() }
        iv_set_back.setOnClickListener { presenter.backButtonClicked() }

        presenter.fragmentViewCreated(exerciseGroup, context!!)
    }

    override fun showExerciseGroup(exerciseGroup: ExerciseGroup) {
        tv_set_title.text = exerciseGroup.name
        exerciseGroupAdapter.setExercises(exerciseGroup.exercises)
        exerciseGroupAdapter.notifyDataSetChanged()
    }

    override fun showExerciseSettings() {
        Toast.makeText(context, "TODO", Toast.LENGTH_SHORT).show()
    }

    override fun returnToPreviousFragment() {
        fragmentManager!!.popBackStack()
    }

    override fun onDestroy() {
        println("fragment:onDestroy")
        super.onDestroy()
        presenter.fragmentDestroyed()
    }
}
