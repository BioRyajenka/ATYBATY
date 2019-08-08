package com.atybaty.timer.view.workoutsettings

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.atybaty.timer.R
import com.atybaty.timer.contract.ExerciseSettingsContract
import com.atybaty.timer.model.Work
import com.atybaty.timer.model.WorkWithAccelerationOptions
import com.atybaty.timer.model.WorkWithIntervalsOptions
import com.atybaty.timer.utils.Seconds
import kotlinx.android.synthetic.main.dialog_settings_exercise.*
import kotlin.math.max
import kotlin.math.min

private const val MAX_DURATION: Seconds = 24 * 60 * 60

class ExerciseSettingsDialog : DialogFragment(), ExerciseSettingsContract.View {

    private lateinit var exerciseSettingsPresenter: ExerciseSettingsContract.Presenter
    private lateinit var work: Work
    private var interval = 0
    private var acceleration = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog!!.setCanceledOnTouchOutside(false)
        dialog!!.window!!.setBackgroundDrawable(resources.getDrawable(R.drawable.dialog_bg))
        return inflater.inflate(R.layout.dialog_settings_exercise, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        exerciseSettingsPresenter.dialogViewCreated(context!!)

        iv_settings_ex_back.setOnClickListener { exerciseSettingsPresenter.backButtonClicked() }
        iv_settings_ex_save.setOnClickListener { exerciseSettingsPresenter.saveButtonClicked() }
        iv_settings_ex_duration_add.setOnClickListener {
            work.duration++
            et_settings_ex_duration_count.setText(work.duration.toString())
            exerciseSettingsPresenter.exerciseDurationSet(work.duration)
        }
        iv_settings_ex_duration_minus.setOnClickListener {
            work.duration--
            et_settings_ex_duration_count.setText(work.duration.toString())
            exerciseSettingsPresenter.exerciseDurationSet(work.duration)
        }
        iv_settings_ex_interval_add.setOnClickListener {
            interval++
            et_settings_ex_interval_count.setText(interval.toString())
            exerciseSettingsPresenter.exerciseIntervalDurationSet(interval)
        }
        iv_settings_ex_interval_minus.setOnClickListener {
            interval--
            et_settings_ex_interval_count.setText(interval.toString())
            exerciseSettingsPresenter.exerciseIntervalDurationSet(interval)
        }
        iv_settings_ex_acceleration_add.setOnClickListener {
            acceleration++
            et_settings_ex_acceleration_count.setText(acceleration.toString())
            exerciseSettingsPresenter.exerciseAccelerationDurationSet(acceleration)
        }
        iv_settings_ex_acceleration_minus.setOnClickListener {
            acceleration--
            et_settings_ex_acceleration_count.setText(acceleration.toString())
            exerciseSettingsPresenter.exerciseAccelerationDurationSet(acceleration)
        }

        et_settings_ex_duration_count.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val newDuration = try {
                    min(MAX_DURATION, max(0, p0.toString().toInt()))
                } catch (_: Exception) {
                    0
                }
                work.duration = newDuration
                exerciseSettingsPresenter.exerciseDurationSet(newDuration)
            }
        })

        et_settings_ex_interval_count.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val newDuration = try {
                    min(MAX_DURATION, max(0, p0.toString().toInt()))
                } catch (_: Exception) {
                    0
                }
                interval = newDuration
                exerciseSettingsPresenter.exerciseIntervalDurationSet(newDuration)
            }
        })

        et_settings_ex_acceleration_count.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val newDuration = try {
                    min(MAX_DURATION, max(0, p0.toString().toInt()))
                } catch (_: Exception) {
                    0
                }
                acceleration = newDuration
                exerciseSettingsPresenter.exerciseAccelerationDurationSet(newDuration)
            }
        })
    }

    override fun closeDialog() {
        dismiss()
    }

    override fun showWork(work: Work) {
        this.work = work
        et_settings_ex_name.setText(work.name)
        et_settings_ex_duration_count.setText(work.duration.toString())
        if (work.options is WorkWithIntervalsOptions) {
            ll_settings_ex_interval_view.visibility = View.VISIBLE
            ll_settings_ex_acceleration_view.visibility = View.GONE
            v_settings_ex_type_inter_indicator.visibility = View.VISIBLE
            v_settings_ex_type_accel_indicator.visibility = View.GONE
            tv_settings_ex_type_inter_btn.setTextColor(resources.getColor(R.color.colorSelected))
            tv_settings_ex_type_accel_btn.setTextColor(resources.getColor(R.color.colorUnselected))
            et_settings_ex_interval_count.setText(work.options.interval.toString())
            interval = work.options.interval
        } else {
            ll_settings_ex_interval_view.visibility = View.GONE
            ll_settings_ex_acceleration_view.visibility = View.VISIBLE
            v_settings_ex_type_inter_indicator.visibility = View.GONE
            v_settings_ex_type_accel_indicator.visibility = View.VISIBLE
            tv_settings_ex_type_inter_btn.setTextColor(resources.getColor(R.color.colorUnselected))
            tv_settings_ex_type_accel_btn.setTextColor(resources.getColor(R.color.colorSelected))
            acceleration = (work.options as WorkWithAccelerationOptions).accelerationDuration
            et_settings_ex_acceleration_count.setText(acceleration.toString())
        }
    }
}