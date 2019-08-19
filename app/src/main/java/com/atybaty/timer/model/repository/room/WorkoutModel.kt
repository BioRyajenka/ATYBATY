package com.atybaty.timer.model.repository.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.atybaty.timer.model.Workout
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

@Entity
class WorkoutModel() {

    constructor(workout: Workout) : this() {
        this.setData(workout)
    }

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    @ColumnInfo
    lateinit var data: String

    @Ignore
    private val mapper = jacksonObjectMapper()

    fun getData(): Workout {
        return mapper.readValue(this.data)
    }

    fun setData(workout: Workout) {
        this.data = mapper.writeValueAsString(workout)
        this.id = workout.id
    }
}