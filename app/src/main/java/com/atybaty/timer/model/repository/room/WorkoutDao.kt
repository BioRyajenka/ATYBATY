package com.atybaty.timer.model.repository.room

import androidx.room.*

@Dao
interface WorkoutModelDao {

    @get:Query("SELECT * FROM WorkoutModel")
    val all: List<WorkoutModel>

    @Query("SELECT * FROM WorkoutModel WHERE id = :id")
    fun getById(id: Int): WorkoutModel

    @Insert
    fun insert(employee: WorkoutModel)

    @Update
    fun update(employee: WorkoutModel)

    @Delete
    fun delete(employee: WorkoutModel)
}