package com.atybaty.timer.view.workoutsettings.workout;

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atybaty.timer.R
import com.atybaty.timer.contract.WorkoutContract
import com.atybaty.timer.model.Workout
import com.atybaty.timer.util.Seconds
import kotlinx.android.synthetic.main.item_train_set.view.*
import kotlin.math.max
import kotlin.math.min

private const val MAX_DURATION: Seconds = 24 * 60 * 60
class ExerciseGroupAdapter(private val presenter: WorkoutContract.Presenter
) : RecyclerView.Adapter<ExerciseGroupHolder>() {

    private lateinit var workout: Workout
    private lateinit var context: Context

    fun setContext(context: Context) {
        this.context = context
    }

    fun setWorkout(workout: Workout) {
        this.workout = workout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseGroupHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_train_set, parent, false)
        val holder = ExerciseGroupHolder(itemView)
        itemView.ll_train_set_btn.setOnClickListener {
            val itemPosition = holder.adapterPosition
            if (itemPosition != RecyclerView.NO_POSITION){
                presenter.exerciseGroupClicked(itemPosition)
            }
        }

        itemView.iv_train_set_delete.setOnClickListener {
            val itemPosition = holder.adapterPosition
            if (itemPosition != RecyclerView.NO_POSITION){
                presenter.deleteButtonClicked(itemPosition)
            }
        }
        itemView.iv_train_set_relax_minus.setOnClickListener {
            val itemPosition = holder.adapterPosition
            if (itemPosition != RecyclerView.NO_POSITION){
                workout.exerciseGroups[itemPosition].relaxAfter.duration--
                presenter.timeAfterExererciseSet(itemPosition, workout.exerciseGroups[itemPosition].relaxAfter.duration)
                notifyItemChanged(itemPosition)
            }
        }
        itemView.iv_train_set_relax_add.setOnClickListener {
            val itemPosition = holder.adapterPosition
            if (itemPosition != RecyclerView.NO_POSITION){
                workout.exerciseGroups[itemPosition].relaxAfter.duration++
                presenter.timeAfterExererciseSet(itemPosition, workout.exerciseGroups[itemPosition].relaxAfter.duration)
                notifyItemChanged(itemPosition)
            }
        }
        itemView.et_train_set_relax_count.addTextChangedListener(object : TextWatcher{

            val itemPosition = holder.adapterPosition

            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val newDuration = try {
                    min(MAX_DURATION, max(0, p0.toString().toInt()))
                } catch (_: Exception) {
                    0
                }
                if (itemPosition != RecyclerView.NO_POSITION){
                    workout.exerciseGroups[itemPosition].relaxAfter.duration = newDuration
                    presenter.timeAfterExererciseSet(itemPosition, newDuration)
                    notifyItemChanged(itemPosition)
                }
            }
        })
        return holder
    }


    override fun getItemCount(): Int {
        return workout.exerciseGroups.size
    }

    override fun onBindViewHolder(holder: ExerciseGroupHolder, position: Int) {
        val name = workout.exerciseGroups[position].name
        holder.setName(name)
        holder.setRelaxTime(workout.exerciseGroups[position].relaxAfter.duration)
    }

}