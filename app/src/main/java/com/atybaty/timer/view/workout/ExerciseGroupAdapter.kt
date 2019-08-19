package com.atybaty.timer.view.workout

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atybaty.timer.R
import com.atybaty.timer.contract.WorkoutContract
import com.atybaty.timer.model.Workout
import kotlinx.android.synthetic.main.item_train_set.view.*

class ExerciseGroupAdapter(
    private val presenter: WorkoutContract.Presenter
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
        itemView.setOnClickListener {
            val itemPosition = holder.adapterPosition
            if (itemPosition != RecyclerView.NO_POSITION) {
                presenter.exerciseGroupClicked(itemPosition)
            }
        }

        itemView.iv_train_set_delete.setOnClickListener {
            val itemPosition = holder.adapterPosition
            if (itemPosition != RecyclerView.NO_POSITION) {
                presenter.deleteButtonClicked(itemPosition)
            }
        }
        return holder
    }


    override fun getItemCount(): Int {
        return workout.exerciseGroups.size
    }

    override fun onBindViewHolder(holder: ExerciseGroupHolder, position: Int) {
        val name = workout.exerciseGroups[position].name
        holder.setName(name)
    }

}