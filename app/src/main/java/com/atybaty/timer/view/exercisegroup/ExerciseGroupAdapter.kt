package com.atybaty.timer.view.exercisegroup

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atybaty.timer.R
import com.atybaty.timer.contract.ExerciseGroupContract
import com.atybaty.timer.model.Exercise
import com.atybaty.timer.model.getExerciseDuration
import com.atybaty.timer.utils.Seconds
import kotlinx.android.synthetic.main.item_exercise.view.*
import kotlin.math.max
import kotlin.math.min

private const val MAX_DURATION: Seconds = 24 * 60 * 60

class ExerciseGroupAdapter(
    private val presenter: ExerciseGroupContract.Presenter
) : RecyclerView.Adapter<ExerciseGroupHolder>() {

    private lateinit var context: Context

    private var exercises: List<Exercise> = emptyList()

    fun setContext(context: Context) {
        this.context = context
    }

    fun setExercises(exercises: List<Exercise>) {
        this.exercises = exercises
    }

    private fun durationChangeOnClickListener(
        holder: ExerciseGroupHolder,
        nestedListener: (exerciseItemPosition: Int, oldDuration: Seconds) -> Unit
    ): View.OnClickListener {
        return View.OnClickListener {
            val itemPosition = holder.adapterPosition
            if (itemPosition != RecyclerView.NO_POSITION) {
                nestedListener(itemPosition, getExerciseDuration(exercises[itemPosition])!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseGroupHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_train_set, parent, false)
        val holder = ExerciseGroupHolder(itemView)
        itemView.iv_exercise_minus.setOnClickListener(durationChangeOnClickListener(holder) { itemPosition, oldDuration ->
            presenter.exerciseDurationSet(itemPosition, max(1, oldDuration - 1))
        })
        itemView.iv_exercise_plus.setOnClickListener(durationChangeOnClickListener(holder) { itemPosition, oldDuration ->
            presenter.exerciseDurationSet(itemPosition, min(MAX_DURATION, oldDuration + 1))
        })
        return holder
    }

    override fun getItemCount() = exercises.size

    override fun onBindViewHolder(holder: ExerciseGroupHolder, position: Int) {
        val exercise = exercises[position]
        holder.itemView.apply {
            tv_exercise_name.text = exercise.getName(context)
            // -1 as an stub
            et_exercise_count.setText((getExerciseDuration(exercise) ?: -1).toString())
        }
    }

}