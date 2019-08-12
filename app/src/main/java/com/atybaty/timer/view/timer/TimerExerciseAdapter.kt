package com.atybaty.timer.view.timer

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atybaty.timer.R
import com.atybaty.timer.contract.TimerContract
import com.atybaty.timer.model.Workout

class TimerExerciseAdapter(val context: Context, val presenter: TimerContract.Presenter) :
    RecyclerView.Adapter<TimerExerciseHolder>() {

    private lateinit var workout: Workout
    private var selectPosition = 0

    fun setWorkout(workout: Workout) {
        this.workout = workout
    }

    fun setSelectPosition(itemPosition: Int) {
        selectPosition = itemPosition
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimerExerciseHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_timer_workout, parent, false)
        val holder = TimerExerciseHolder(context, view)
        view.setOnClickListener {
            val itemPosition = holder.adapterPosition
            if (itemPosition != RecyclerView.NO_POSITION) {
                presenter.itemExerciseClicked(itemPosition)
            }
        }
        return holder
    }

    override fun getItemCount(): Int {
        //count exercise with coolDown
        var itemCount = 1
        workout.exerciseGroups.forEach {
            itemCount += it.exercises.size + 1
        }
        return itemCount
    }

    override fun onBindViewHolder(holder: TimerExerciseHolder, position: Int) {
        if (position == selectPosition) {
            holder.selectExercise()
        } else {
            holder.unselectExercise()
        }
        when (position) {
            itemCount - 1 -> holder.showExercise("Заминка", workout.coolDown)
            else -> {
                var count = 0
                for (i in 0 until workout.exerciseGroups.size) {
                    if (count == position) {
                        holder.showExerciseGroupName(workout.exerciseGroups[i].name)
                        break
                    } else {
                        for (j in 0 until workout.exerciseGroups[i].exercises.size) {
                            count++
                            if (count == position) {
                                val exercise = workout.exerciseGroups[i].exercises[j]
                                holder.showExercise(exercise.getName(context), exercise.duration)
                                holder.showNumber(j + 1)
                                break
                            }
                        }
                    }
                    count++
                }
            }
        }
    }
}