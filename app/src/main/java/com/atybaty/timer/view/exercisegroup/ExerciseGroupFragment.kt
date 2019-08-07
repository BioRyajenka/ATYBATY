package com.atybaty.timer.view.exercisegroup

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
import kotlinx.android.synthetic.main.fragment_set.view.*

class ExerciseGroupFragment : Fragment(), ExerciseGroupContract.View {
    private lateinit var exerciseGroup: ExerciseGroup

    private val presenter = ExerciseGroupPresenter(this, context!!)
    private val exerciseGroupAdapter = ExerciseGroupAdapter(presenter)

    fun setExerciseGroup(exerciseGroup: ExerciseGroup) {
        this.exerciseGroup = exerciseGroup
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        println("fragment:onCreateView")

        exerciseGroupAdapter.setContext(context!!)
        return inflater.inflate(R.layout.fragment_set, container, false).apply {
            rv_set_exercise.adapter = exerciseGroupAdapter
            rv_set_exercise.layoutManager = LinearLayoutManager(context)
        }.also {
            presenter.fragmentViewCreated(exerciseGroup)
        }
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
