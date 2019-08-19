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
import com.atybaty.timer.util.Seconds

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

        val work = (CurrentWorkoutHolder.currentWorkout.exerciseGroups[CurrentWorkoutHolder.currenExerciseGroupPosition]
            .exercises[CurrentWorkoutHolder.currentWorkPosition] as Work).deepCopy()
        when (work.options) {
            is SimpleWorkOptions -> {
                intervalWork = work.deepCopy()
                intervalWork.options = defaultIntervalWorkOptions
                accelerationWork = work.deepCopy()
                accelerationWork.options = defaultAccelerationWorkOptions
                currentWorkType = WorkType.INTERVAL
            }
            is WorkWithIntervalsOptions -> {
                intervalWork = work.deepCopy()
                accelerationWork = work.deepCopy()
                accelerationWork.options = defaultAccelerationWorkOptions
                currentWorkType = WorkType.INTERVAL
            }
            is WorkWithAccelerationOptions -> {
                intervalWork = work.deepCopy()
                intervalWork.options = defaultAccelerationWorkOptions
                accelerationWork = work.deepCopy()
                currentWorkType = WorkType.ACCELERATION
            }
        }
        check(intervalWork.options is WorkWithIntervalsOptions)
        check(accelerationWork.options is WorkWithAccelerationOptions)

        workTypeSelected(currentWorkType)
    }

    override fun dialogDismissed(listener: () -> Unit) {
        listener()
    }

    override fun exerciseNameSet(newName: String) {
        intervalWork.name = newName
        accelerationWork.name = newName
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
        val resultingWork = getSelectedWork().let {
            if (it.options is WorkWithIntervalsOptions && (it.options as WorkWithIntervalsOptions).interval == 0
                || it.options is WorkWithAccelerationOptions && (it.options as WorkWithAccelerationOptions).accelerationDuration == 0) {
                it.deepCopy()
            } else {
                it
            }
        }
        CurrentWorkoutHolder.currentWorkout.exerciseGroups[CurrentWorkoutHolder.currenExerciseGroupPosition]
            .exercises.set(CurrentWorkoutHolder.currentWorkPosition, resultingWork)
        workoutRepository.saveWorkout(CurrentWorkoutHolder.currentWorkout)
        view.closeDialog()
    }

    override fun workTypeSelected(workType: WorkType) {
        currentWorkType = workType
        view.showWork(getSelectedWork())
    }

    override fun exerciseDurationSet(newDuration: Seconds) {
        getSelectedWork().duration = newDuration
    }

    override fun exerciseIntervalDurationSet(newDuration: Seconds) {
        (intervalWork.options as WorkWithIntervalsOptions).interval = newDuration
    }

    override fun exerciseAccelerationDurationSet(newDuration: Seconds) {
        (accelerationWork.options as WorkWithAccelerationOptions).accelerationDuration = newDuration
    }

}
