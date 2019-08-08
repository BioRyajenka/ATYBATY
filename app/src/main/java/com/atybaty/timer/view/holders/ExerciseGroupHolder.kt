package com.atybaty.timer.view.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.fragment_workout.view.*
import kotlinx.android.synthetic.main.item_train_set.view.*

class ExerciseGroupHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setName(name: String){
        itemView.tv_train_set_name.text = name;
    }
}