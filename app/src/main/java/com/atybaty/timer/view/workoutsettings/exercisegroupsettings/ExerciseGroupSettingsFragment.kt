package com.atybaty.timer.view.workoutsettings.exercisegroupsettings

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.atybaty.timer.R
import com.atybaty.timer.contract.ExerciseGroupSettingsContract
import com.atybaty.timer.model.ExerciseGroup
import com.atybaty.timer.presenter.ExerciseGroupSettingsPresenter
import com.atybaty.timer.util.Seconds
import com.atybaty.timer.view.workoutsettings.exercisegroup.ExerciseGroupFragment
import kotlinx.android.synthetic.main.fragment_exercisegroup_settings.*
import kotlin.math.max
import kotlin.math.min

private const val MAX_DURATION: Seconds = 24 * 60 * 60
class ExerciseGroupSettingsFragment: Fragment(), ExerciseGroupSettingsContract.View {

    private lateinit var exerciseGroupSettingsPresenter: ExerciseGroupSettingsContract.Presenter
    private lateinit var exerciseGroup: ExerciseGroup

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_exercisegroup_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exerciseGroupSettingsPresenter = ExerciseGroupSettingsPresenter(this)

        iv_work_back.setOnClickListener { exerciseGroupSettingsPresenter.backButtonClicked() }
        iv_work_save.setOnClickListener { exerciseGroupSettingsPresenter.saveButtonClicked() }
        tv_work_settings.setOnClickListener { exerciseGroupSettingsPresenter.changeWorkButtonClicked() }

        iv_work_starttime_add.setOnClickListener {
            exerciseGroup.warmUp++
            exerciseGroupSettingsPresenter.startTimeSet(exerciseGroup.warmUp)
            updateTimeInformation()
        }
        iv_work_starttime_minus.setOnClickListener {
            exerciseGroup.warmUp--
            exerciseGroupSettingsPresenter.startTimeSet(exerciseGroup.warmUp)
            updateTimeInformation()
        }
        et_work_starttime_count.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val newDuration = try {
                    min(MAX_DURATION, max(0, p0.toString().toInt()))
                } catch (_: Exception) {
                    0
                }
                exerciseGroup.warmUp = newDuration
                exerciseGroupSettingsPresenter.startTimeSet(newDuration)
            }
        })

        iv_work_time_add.setOnClickListener {
            exerciseGroup.defaultTime++
            exerciseGroupSettingsPresenter.defaultTimeSet(exerciseGroup.defaultTime)
            updateTimeInformation()
        }
        iv_work_time_minus.setOnClickListener {
            exerciseGroup.defaultTime--
            exerciseGroupSettingsPresenter.defaultTimeSet(exerciseGroup.defaultTime)
            updateTimeInformation()
        }
        et_work_time_count.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val newDuration = try {
                    min(MAX_DURATION, max(0, p0.toString().toInt()))
                } catch (_: Exception) {
                    0
                }
                if (exerciseGroup.defaultTime != newDuration) {
                    exerciseGroup.defaultTime = newDuration
                    exerciseGroupSettingsPresenter.defaultTimeSet(newDuration)
                }
            }
        })

        iv_work_relaxtime_add.setOnClickListener {
            exerciseGroup.relaxTime++
            exerciseGroupSettingsPresenter.relaxTimeSet(exerciseGroup.relaxTime)
            updateTimeInformation()
        }
        iv_work_relaxtime_minus.setOnClickListener {
            exerciseGroup.relaxTime--
            exerciseGroupSettingsPresenter.relaxTimeSet(exerciseGroup.relaxTime)
            updateTimeInformation()
        }
        et_work_relaxtime_count.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val newDuration = try {
                    min(MAX_DURATION, max(0, p0.toString().toInt()))
                } catch (_: Exception) {
                    0
                }
                if (exerciseGroup.relaxTime != newDuration) {
                    exerciseGroup.relaxTime = newDuration
                    exerciseGroupSettingsPresenter.relaxTimeSet(newDuration)
                }
            }
        })

        iv_work_repeats_add.setOnClickListener {
            exerciseGroup.repeatsCount++
            exerciseGroupSettingsPresenter.repeatsCountSet(exerciseGroup.repeatsCount)
            updateTimeInformation()
        }
        iv_work_repeats_minus.setOnClickListener {
            exerciseGroup.repeatsCount--
            exerciseGroupSettingsPresenter.repeatsCountSet(exerciseGroup.repeatsCount)
            updateTimeInformation()
        }
        et_work_repeats_count.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val newDuration = try {
                    min(MAX_DURATION, max(0, p0.toString().toInt()))
                } catch (_: Exception) {
                    0
                }
                exerciseGroup.repeatsCount = newDuration
                exerciseGroupSettingsPresenter.repeatsCountSet(newDuration)
            }
        })

        exerciseGroupSettingsPresenter.fragmentViewCreated(context!!)
    }

    override fun showExerciseGroup(exerciseGroup: ExerciseGroup) {
        this.exerciseGroup = exerciseGroup
        tv_work_title.text = exerciseGroup.name
        et_work_starttime_count.setText(exerciseGroup.warmUp.toString())
        et_work_time_count.setText(exerciseGroup.defaultTime.toString())
        et_work_relaxtime_count.setText(exerciseGroup.relaxTime.toString())
        et_work_repeats_count.setText(exerciseGroup.repeatsCount.toString())
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
        activity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.fl_train_frames, ExerciseGroupFragment()).addToBackStack("show exercise group info").commit()
    }

    private fun updateTimeInformation(){
        et_work_starttime_count.setText(exerciseGroup.warmUp.toString())
        et_work_time_count.setText(exerciseGroup.defaultTime.toString())
        et_work_relaxtime_count.setText(exerciseGroup.relaxTime.toString())
        et_work_repeats_count.setText(exerciseGroup.repeatsCount.toString())
    }
}