package com.atybaty.timer.view.workoutlist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_train.view.*

class WorkoutHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setName(name: String){
        itemView.tv_train_name.text = name
    }

    fun setCountSets(count: Int){
        itemView.tv_train_countset.text = "Сеты: $count"
    }
}
