package com.atybaty.timer.view.workoutsettings.exercisegroupsettings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.atybaty.timer.R
import com.atybaty.timer.contract.ExerciseGroupSettingsContract
import com.atybaty.timer.model.ExerciseGroup
import com.atybaty.timer.presenter.ExerciseGroupSettingsPresenter
import com.atybaty.timer.utils.Seconds
import kotlinx.android.synthetic.main.fragment_exercisegroup_settings.*

class ExerciseGroupSettingsFragment: Fragment(), ExerciseGroupSettingsContract.View {

    private lateinit var exerciseGroupSettingsPresenter: ExerciseGroupSettingsContract.Presenter
    private lateinit var exerciseGroup: ExerciseGroup

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_exercisegroup_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        exerciseGroupSettingsPresenter = ExerciseGroupSettingsPresenter(this)
    }

    override fun showExerciseGroup(exerciseGroup: ExerciseGroup) {
        this.exerciseGroup = exerciseGroup
    }

    override fun updateStartTime(time: Seconds) {
        et_work_starttime_count.setText(time.toString())
    }

    override fun updateDefaultTime(time: Seconds) {
        et_work_time_count.setText(time.toString())
    }

    override fun updateRelaxTime(time: Seconds) {
        et_work_relaxtime_count.setText(time.toString())
    }

    override fun updateRepeatsCount(count: Int) {
        et_work_repeats_count.setText(count.toString())
    }

    override fun hideChangeMessage() {
        tv_work_flag.visibility = View.GONE
    }

    override fun showChangeMessage() {
        tv_work_flag.visibility = View.VISIBLE
    }

    override fun showPreviousScreen() {
        activity!!.supportFragmentManager.popBackStack()
    }

    override fun showExerciseSettings() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}