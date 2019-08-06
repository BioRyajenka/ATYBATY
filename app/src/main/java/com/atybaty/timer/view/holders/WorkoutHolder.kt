package com.atybaty.timer.view.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_train.view.*

class WorkoutHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setName(name: String){
        itemView.tv_train_name.text = name
    }

    fun setStartTime(startTime: Int){
        itemView.tv_train_starttime.text = "Подготовка: $startTime сек"
    }

    fun setCountSets(count: Int){
        itemView.tv_train_countset.text = "Сеты: $count"
    }

    fun setEndTime(endTime: Int){
        itemView.tv_train_endtime.text = "Заминка: $endTime сек"
    }
}