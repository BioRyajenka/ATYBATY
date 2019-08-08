package com.atybaty.timer.view.exercisegroup

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.atybaty.timer.R
import com.atybaty.timer.contract.ExerciseGroupContract
import com.atybaty.timer.model.*
import com.atybaty.timer.utils.Seconds
import kotlin.math.max
import kotlin.math.min

private const val MAX_DURATION: Seconds = 24 * 60 * 60

private const val VIEWTYPE_WORK = 0
private const val VIEWTYPE_REST = 1

class ExerciseGroupAdapter(
    private val presenter: ExerciseGroupContract.Presenter
) : RecyclerView.Adapter<ExerciseHolder>() {

    private lateinit var context: Context

    private var exercises: List<Exercise> = emptyList()

    fun setContext(context: Context) {
        this.context = context
    }

    fun setExercises(exercises: List<Exercise>) {
        this.exercises = exercises
    }

    private fun durationChangeOnClickListener(
        holder: ExerciseHolder,
        nestedListener: (exerciseItemPosition: Int, oldDuration: Seconds) -> Unit
    ): View.OnClickListener {
        return View.OnClickListener {
            val itemPosition = holder.adapterPosition
            if (itemPosition != RecyclerView.NO_POSITION) {
                nestedListener(itemPosition, exercises[itemPosition].duration)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseHolder {
        val layoutId = if (viewType == VIEWTYPE_WORK) {
            R.layout.item_exercise_work
        } else {
            R.layout.item_exercise_rest
        }
        val itemView = LayoutInflater.from(context).inflate(layoutId, parent, false)
        val holder = ExerciseHolder(itemView)

        itemView.findViewById<ImageView>(R.id.iv_exercise_minus)
            .setOnClickListener(durationChangeOnClickListener(holder) { itemPosition, oldDuration ->
                val newDuration = max(0, oldDuration - 1)
                exercises[itemPosition].duration = newDuration
                presenter.exerciseDurationSet(itemPosition, newDuration)
            })
        itemView.findViewById<ImageView>(R.id.iv_exercise_plus)
            .setOnClickListener(durationChangeOnClickListener(holder) { itemPosition, oldDuration ->
                val newDuration = min(MAX_DURATION, oldDuration + 1)
                exercises[itemPosition].duration = newDuration
                presenter.exerciseDurationSet(itemPosition, newDuration)
            })
        itemView.findViewById<EditText>(R.id.et_exercise_duration).addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val newDuration = try {
                    min(MAX_DURATION, max(0, s.toString().toInt()))
                } catch (_: Exception) {
                    0
                }
                val itemPosition = holder.adapterPosition
                presenter.exerciseDurationSet(itemPosition, newDuration)
                exercises[itemPosition].duration = newDuration
            }
        })

        if (viewType == VIEWTYPE_WORK) {
            itemView.findViewById<TextView>(R.id.tv_setup_exercise).setOnClickListener {
                presenter.setUpExerciseButtonClicked(holder.adapterPosition)
            }
        }

        return holder
    }

    override fun getItemCount() = exercises.size

    override fun getItemViewType(position: Int): Int {
        return when (exercises[position]) {
            is Work -> VIEWTYPE_WORK
            else -> VIEWTYPE_REST
        }
    }

    override fun onBindViewHolder(holder: ExerciseHolder, position: Int) {
        val exercise = exercises[position]
        holder.itemView.apply {
            if (exercise is Work) {
                findViewById<TextView>(R.id.tv_exercise_options_desc).text = when (val options = exercise.options) {
                    is SimpleWorkOptions -> ""
                    is WorkWithIntervalsOptions -> "Интервалы: ${options.interval}"
                    is WorkWithAccelerationOptions -> "Ускорение: ${options.accelerationDuration}"
                }
            }
            findViewById<TextView>(R.id.tv_exercise_name).text = exercise.getName(context)
            findViewById<EditText>(R.id.et_exercise_duration).setText(exercise.duration.toString())
        }
    }

}
