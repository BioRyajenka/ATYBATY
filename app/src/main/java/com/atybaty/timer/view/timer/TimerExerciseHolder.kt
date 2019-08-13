package com.atybaty.timer.view.timer

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.atybaty.timer.R
import com.atybaty.timer.model.Exercise
import com.atybaty.timer.model.ExerciseGroup
import com.atybaty.timer.util.Seconds
import kotlinx.android.synthetic.main.item_timer_exercise.view.*
import kotlinx.android.synthetic.main.item_timer_exercise.view.ll_timer_exercise_view
import kotlinx.android.synthetic.main.item_timer_exercisegroup.view.*

class TimerExerciseHolder(val context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun selectExercise(){
        itemView.ll_timer_exercise_view.setBackgroundColor(context.resources.getColor(R.color.colorPrimary))
    }

    fun unselectExercise(){
        itemView.ll_timer_exercise_view.setBackgroundColor(context.resources.getColor(R.color.colorPrimaryDark))
    }

    fun showExerciseGroup(exerciseGroup: ExerciseGroup) {
        itemView.tv_timer_exercisegroup_name.text = exerciseGroup.name
    }

    fun showExercise(exercise: Exercise, exerciseIndex: Int){
        itemView.tv_timer_exercise_number.text = "${exerciseIndex + 1}."
        itemView.tv_timer_exercise_name.text = "${exercise.getName(context)}: ${exercise.duration}"
    }
}
