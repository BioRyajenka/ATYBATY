package com.atybaty.timer.view.timer

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atybaty.timer.R
import com.atybaty.timer.contract.TimerContract
import com.atybaty.timer.model.Exercise
import com.atybaty.timer.model.ExerciseGroup
import com.atybaty.timer.model.Workout
import kotlin.properties.Delegates

private const val VIEWTYPE_EXERCISEGROUP = 0
private const val VIEWTYPE_EXERCISE = 1

private data class ListItemHolder(val exerciseOrGroup: Any, val exerciseGroupIndex: Int, val exerciseIndex: Int) {
    init {
        require(exerciseIndex >= -1)
        require(exerciseGroupIndex >= 0)
    }
}

class TimerExerciseAdapter(private val context: Context,
                           private val presenter: TimerContract.Presenter) :
    RecyclerView.Adapter<TimerExerciseHolder>() {

    private var itemsColorId: Int = R.color.colorPrimaryDark // just stub color. affects nothing
    private var selectedExerciseGroupIndex: Int by Delegates.notNull()
    private var selectedExerciseIndex: Int by Delegates.notNull()

    private lateinit var listItems: List<ListItemHolder>

    init {
        clearExerciseSelection()
    }

    fun setWorkout(workout: Workout) {
        listItems = workout.exerciseGroups.withIndex().flatMap { (egIndex, eg) ->
            listOf(ListItemHolder(eg, egIndex,-1)) + eg.exercises.mapIndexed { eIndex, e ->
                ListItemHolder(e, egIndex, eIndex)
            }
        }
    }

    fun setSelectedExercise(exerciseGroupIndex: Int, exerciseIndex: Int) {
        this.selectedExerciseGroupIndex = exerciseGroupIndex
        this.selectedExerciseIndex = exerciseIndex
    }

    fun clearExerciseSelection() {
        this.selectedExerciseGroupIndex = -1
        this.selectedExerciseIndex = -1
    }

    fun setItemsColor(colorId: Int) {
        itemsColorId = colorId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimerExerciseHolder {
        val layoutId = if (viewType == VIEWTYPE_EXERCISEGROUP) {
            R.layout.item_timer_exercisegroup
        } else {
            check(viewType == VIEWTYPE_EXERCISE)
            R.layout.item_timer_exercise
        }
        val view = LayoutInflater.from(context).inflate(layoutId, parent, false)
        val holder = TimerExerciseHolder(context, view)

        view.setOnClickListener {
            val itemPosition = holder.adapterPosition
            if (itemPosition != RecyclerView.NO_POSITION) {
                with(listItems[itemPosition]) {
                    if (exerciseIndex != -1) {
                        presenter.itemExerciseClicked(exerciseGroupIndex, exerciseIndex)
                    }
                }
            }
        }
        return holder
    }

    override fun getItemViewType(position: Int): Int {
        return if (listItems[position].exerciseOrGroup is ExerciseGroup) {
            VIEWTYPE_EXERCISEGROUP
        } else {
            VIEWTYPE_EXERCISE
        }
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun onBindViewHolder(holder: TimerExerciseHolder, position: Int) {
        holder.setColor(itemsColorId)
        if (listItems[position].exerciseGroupIndex == selectedExerciseGroupIndex &&
            listItems[position].exerciseIndex == selectedExerciseIndex
        ) {
            holder.selectExercise()
        } else {
            holder.unselectExercise()
        }
        with(listItems[position]) {
            if (exerciseOrGroup is ExerciseGroup) {
                holder.showExerciseGroup(exerciseOrGroup)
            } else {
                holder.showExercise(exerciseOrGroup as Exercise, exerciseIndex)
            }
        }
    }
}
