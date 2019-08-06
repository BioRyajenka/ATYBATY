package com.atybaty.timer.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atybaty.timer.R
import com.atybaty.timer.contract.IWorkoutListContract
import com.atybaty.timer.model.Workout
import com.atybaty.timer.view.holders.WorkoutHolder
import kotlinx.android.synthetic.main.item_train.view.*

class WorkoutAdapter(val context: Context, val presenter: IWorkoutListContract.Presenter): RecyclerView.Adapter<WorkoutHolder>() {

    private lateinit var trains: ArrayList<Workout>

    fun setWorkouts(workouts: ArrayList<Workout>){
        trains = workouts
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_train, parent, false)
        val holder = WorkoutHolder(itemView)
        itemView.iv_train_delete.setOnClickListener {
            val itemPosition = holder.adapterPosition
            if (itemPosition != RecyclerView.NO_POSITION){
                trains.removeAt(itemPosition)
                presenter.deleteButtonClicked(itemPosition)
                notifyItemRemoved(itemPosition)
            }
        }
        itemView.iv_train_play.setOnClickListener {
            val itemPosition = holder.adapterPosition
            if (itemPosition != RecyclerView.NO_POSITION){
                presenter.playButtonCLicked(itemPosition)
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
        return trains.size
    }

    override fun onBindViewHolder(holder: WorkoutHolder, position: Int) {
        val train = trains[position]
        holder.setStartTime(train.warmUp)
        holder.setCountSets(train.exerciseGroups.size)
        holder.setEndTime(train.coolDown)
    }


}