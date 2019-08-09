package com.atybaty.timer.presenter

import android.content.Context
import com.atybaty.timer.contract.ExerciseSettingsContract
import com.atybaty.timer.contract.ExerciseSettingsContract.Presenter.WorkType
import com.atybaty.timer.dataholders.CurrentWorkoutHolder
import com.atybaty.timer.dataholders.WorkoutRepositoryHolder
import com.atybaty.timer.model.SimpleWorkOptions
import com.atybaty.timer.model.Work
import com.atybaty.timer.model.WorkWithAccelerationOptions
import com.atybaty.timer.model.WorkWithIntervalsOptions
import com.atybaty.timer.model.repository.WorkoutRepository
import com.atybaty.timer.utils.Seconds

class ExerciseSettingsPresenter(val view: ExerciseSettingsContract.View) : ExerciseSettingsContract.Presenter {
    private lateinit var context: Context
    private lateinit var workoutRepository: WorkoutRepository

    private lateinit var intervalWork: Work
    private lateinit var accelerationWork: Work
    private lateinit var currentWorkType: WorkType

    override fun dialogViewCreated(context: Context) {
        this.context = context
        this.workoutRepository = WorkoutRepositoryHolder.getWorkoutRepository(context)

        val defaultIntervalWorkOptions = WorkWithIntervalsOptions(0, 0)
        val defaultAccelerationWorkOptions = WorkWithAccelerationOptions(0)

        val work = CurrentWorkoutHolder.currentWork
        when (work.options) {
            is SimpleWorkOptions -> {
                intervalWork = work.copy(options = defaultIntervalWorkOptions)
                accelerationWork = work.copy(options = defaultAccelerationWorkOptions)
                currentWorkType = WorkType.INTERVAL
            }
            is WorkWithIntervalsOptions -> {
                intervalWork = work.copy()
                accelerationWork = work.copy(options = defaultAccelerationWorkOptions)
                currentWorkType = WorkType.ACCELERATION
            }
            is WorkWithAccelerationOptions -> {
                intervalWork = work.copy(options = defaultIntervalWorkOptions)
                accelerationWork = work.copy()
                currentWorkType = WorkType.INTERVAL
            }
        }
        check(intervalWork.options is WorkWithIntervalsOptions)
        check(accelerationWork.options is WorkWithAccelerationOptions)
    }

    override fun dialogDestroyed() {
    }

    override fun backButtonClicked() {
        view.closeDialog()
    }

    private fun getSelectedWork(): Work {
        return if (currentWorkType == WorkType.INTERVAL) {
            intervalWork
        } else {
            accelerationWork
        }
    }

    override fun saveButtonClicked() {
        val currentExerciseIndex = CurrentWorkoutHolder.currentExerciseGroup.exercises
            .indexOf(CurrentWorkoutHolder.currentWork)
        val selectedWork = getSelectedWork()
        CurrentWorkoutHolder.currentExerciseGroup.exercises[currentExerciseIndex] = selectedWork
        workoutRepository.saveWorkout(CurrentWorkoutHolder.currentWorkout)
        view.closeDialog()
    }

    override fun workTypeSelected(workType: WorkType) {
        view.showWork(getSelectedWork())
    }

    override fun exerciseDurationSet(newDuration: Seconds) {
        getSelectedWork().duration = newDuration
    }

    override fun exerciseIntervalDurationSet(newDuration: Seconds) {
        (intervalWork.options as WorkWithIntervalsOptions).interval = newDuration
    }

    override fun exerciseAccelerationDurationSet(newDuration: Seconds) {
        (intervalWork.options as WorkWithAccelerationOptions).accelerationDuration = newDuration
    }

}
