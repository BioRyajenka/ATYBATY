package com.atybaty.timer.view.workoutsettings.workout

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.atybaty.timer.util.Seconds

import kotlinx.android.synthetic.main.item_train_set.view.*

class ExerciseGroupHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setName(name: String){
        itemView.tv_train_set_name.text = name
    }

    fun setRelaxTime(time: Seconds){
        itemView.et_train_set_relax_count.setText(time.toString())
    }
}
