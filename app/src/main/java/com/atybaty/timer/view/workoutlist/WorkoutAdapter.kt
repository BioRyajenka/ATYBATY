package com.atybaty.timer.view.workoutlist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atybaty.timer.R
import com.atybaty.timer.contract.WorkoutListContract
import com.atybaty.timer.model.Workout
import kotlinx.android.synthetic.main.item_workout.view.*

class WorkoutAdapter(
    private val context: Context,
    private val presenter: WorkoutListContract.Presenter): RecyclerView.Adapter<WorkoutHolder>() {

    private var workouts: List<Workout> = emptyList()

    fun setWorkouts(workouts: List<Workout>){
        this.workouts = workouts
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_workout, parent, false)
        val holder = WorkoutHolder(itemView)
        itemView.iv_train_delete.setOnClickListener {
            val itemPosition = holder.adapterPosition
            if (itemPosition != RecyclerView.NO_POSITION){
                presenter.deleteButtonClicked(itemPosition)
            }
        }
        itemView.iv_train_play.setOnClickListener {
            val itemPosition = holder.adapterPosition
            if (itemPosition != RecyclerView.NO_POSITION){
                presenter.playButtonClicked(itemPosition)
            }
        }
        itemView.setOnClickListener {
            val itemPosition = holder.adapterPosition
            if (itemPosition != RecyclerView.NO_POSITION){
                presenter.itemClicked(itemPosition)
            }
        }
        return holder
    }

    override fun getItemCount(): Int {
        return workouts.size
    }

    override fun onBindViewHolder(holder: WorkoutHolder, position: Int) {
        val workout = workouts[position]
        holder.setName(workout.name)
        holder.setCountSets(workout.exerciseGroups.size)
    }
}
