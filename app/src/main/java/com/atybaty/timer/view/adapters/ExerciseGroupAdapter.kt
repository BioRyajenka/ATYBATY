package com.atybaty.timer.view.adapters;

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atybaty.timer.R
import com.atybaty.timer.WorkoutRepositoryHolder
import com.atybaty.timer.contract.WorkoutContract
import com.atybaty.timer.model.ExerciseGroup
import com.atybaty.timer.model.Workout
import com.atybaty.timer.view.holders.ExerciseGroupHolder
import com.atybaty.timer.view.holders.WorkoutHolder

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
        return holder
    }


    override fun getItemCount(): Int {
        return workout.exerciseGroups.size
    }

    override fun onBindViewHolder(holder: ExerciseGroupHolder, position: Int) {
        // TODO("proper name")
        // val exerciseGroup = workout.exerciseGroups[position]
        holder.setName("Сет" + position)
    }



}