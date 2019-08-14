package com.atybaty.timer.view.workoutsettings.exercisesettings

import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.atybaty.timer.R
import com.atybaty.timer.contract.ExerciseSettingsContract
import com.atybaty.timer.contract.ExerciseSettingsContract.Presenter.WorkType
import com.atybaty.timer.model.Work
import com.atybaty.timer.model.WorkWithAccelerationOptions
import com.atybaty.timer.model.WorkWithIntervalsOptions
import com.atybaty.timer.presenter.ExerciseSettingsPresenter
import com.atybaty.timer.utils.Seconds
import kotlinx.android.synthetic.main.dialog_settings_exercise.*
import kotlin.math.max
import kotlin.math.min

private const val MAX_DURATION: Seconds = 24 * 60 * 60

class ExerciseSettingsDialog(private val onDismissListener: () -> Unit) : DialogFragment(), ExerciseSettingsContract.View {

    private val presenter = ExerciseSettingsPresenter(this)
    private lateinit var work: Work
    private var interval = 0
    private var acceleration = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_settings_exercise, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog!!.setCanceledOnTouchOutside(false)
        dialog!!.window!!.setBackgroundDrawable(resources.getDrawable(R.drawable.dialog_bg))

        iv_settings_ex_back.setOnClickListener { presenter.backButtonClicked() }
        iv_settings_ex_save.setOnClickListener { presenter.saveButtonClicked() }

        et_settings_ex_name.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                presenter.exerciseNameSet(s.toString())
            }

        })

        iv_settings_ex_duration_add.setOnClickListener {
            work.duration++
            et_settings_ex_duration_count.setText(work.duration.toString())
            presenter.exerciseDurationSet(work.duration)
        }
        iv_settings_ex_duration_minus.setOnClickListener {
            work.duration--
            et_settings_ex_duration_count.setText(work.duration.toString())
            presenter.exerciseDurationSet(work.duration)
        }
        iv_settings_ex_interval_add.setOnClickListener {
            interval++
            et_settings_ex_interval_count.setText(interval.toString())
            presenter.exerciseIntervalDurationSet(interval)
        }
        iv_settings_ex_interval_minus.setOnClickListener {
            interval--
            et_settings_ex_interval_count.setText(interval.toString())
            presenter.exerciseIntervalDurationSet(interval)
        }
        iv_settings_ex_acceleration_add.setOnClickListener {
            acceleration++
            et_settings_ex_acceleration_count.setText(acceleration.toString())
            presenter.exerciseAccelerationDurationSet(acceleration)
        }
        iv_settings_ex_acceleration_minus.setOnClickListener {
            acceleration--
            et_settings_ex_acceleration_count.setText(acceleration.toString())
            presenter.exerciseAccelerationDurationSet(acceleration)
        }

        et_settings_ex_duration_count.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val newDuration = try {
                    min(MAX_DURATION, max(0, p0.toString().toInt()))
                } catch (_: Exception) {
                    0
                }
                work.duration = newDuration
                presenter.exerciseDurationSet(newDuration)
            }
        })

        et_settings_ex_interval_count.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val newDuration = try {
                    min(MAX_DURATION, max(0, p0.toString().toInt()))
                } catch (_: Exception) {
                    0
                }
                interval = newDuration
                presenter.exerciseIntervalDurationSet(newDuration)
            }
        })

        et_settings_ex_acceleration_count.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val newDuration = try {
                    min(MAX_DURATION, max(0, p0.toString().toInt()))
                } catch (_: Exception) {
                    0
                }
                acceleration = newDuration
                presenter.exerciseAccelerationDurationSet(newDuration)
            }
        })
        tv_settings_ex_type_accel_btn.setOnClickListener {
            presenter.workTypeSelected(WorkType.ACCELERATION)
        }
        tv_settings_ex_type_inter_btn.setOnClickListener {
            presenter.workTypeSelected(WorkType.INTERVAL)
        }

        presenter.dialogViewCreated(context!!)
    }

    override fun closeDialog() {
        dismiss()
    }

    override fun showWork(work: Work) {
        this.work = work

        et_settings_ex_name.setText(work.name)
        et_settings_ex_duration_count.setText(work.duration.toString())

        setIntervalVisible(work.options is WorkWithIntervalsOptions)
        if (work.options is WorkWithIntervalsOptions) {
            et_settings_ex_interval_count.setText((work.options as WorkWithIntervalsOptions).interval.toString())
            interval = (work.options as WorkWithIntervalsOptions).interval
        } else {
            acceleration = (work.options as WorkWithAccelerationOptions).accelerationDuration
            et_settings_ex_acceleration_count.setText(acceleration.toString())
        }
    }

    private fun setIntervalVisible(intervalVisible: Boolean) {
        val (intervalVisibility, accelerationVisibility) = if (intervalVisible) {
            View.VISIBLE to View.GONE
        } else {
            View.GONE to View.VISIBLE
        }
        val (intervalIndicatorVisibility, accelerationIndicatorVisibility) = if (intervalVisible) {
            View.VISIBLE to View.INVISIBLE
        } else {
            View.INVISIBLE to View.VISIBLE
        }
        val (intervalColor, accelerationColor) = if (intervalVisible) {
            R.color.colorSelected to R.color.colorUnselected
        } else {
            R.color.colorUnselected to R.color.colorSelected
        }

        ll_settings_ex_interval_view.visibility = intervalVisibility
        v_settings_ex_type_inter_indicator.visibility = intervalIndicatorVisibility
        tv_settings_ex_type_inter_btn.setTextColor(resources.getColor(intervalColor))

        ll_settings_ex_acceleration_view.visibility = accelerationVisibility
        v_settings_ex_type_accel_indicator.visibility = accelerationIndicatorVisibility
        tv_settings_ex_type_accel_btn.setTextColor(resources.getColor(accelerationColor))
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
        presenter.dialogDismissed(onDismissListener)
    }
}
