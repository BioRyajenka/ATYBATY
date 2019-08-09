package com.atybaty.timer.view.timer

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.atybaty.timer.R
import com.atybaty.timer.utils.Seconds
import kotlinx.android.synthetic.main.item_timer_workout.view.*

class TimerExerciseHolder(val context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun selectExercise(){
        itemView.ll_timer_exercise_view.setBackgroundColor(context.resources.getColor(R.color.colorPrimary))
    }

    fun unselectExercise(){
        itemView.ll_timer_exercise_view.setBackgroundColor(context.resources.getColor(R.color.colorPrimaryDark))
    }

    fun showNumber(number: Int){
        itemView.tv_timer_exercise_number.text = "$number."
    }

    fun showExercise(name: String, duration: Seconds){
        itemView.tv_timer_exercise_name.text = "$name: $duration"
    }

    fun showExerciseGroupName(name: String){
        itemView.tv_timer_exercise_number.visibility = View.GONE
        itemView.tv_timer_exercise_name.text = name
    }
}