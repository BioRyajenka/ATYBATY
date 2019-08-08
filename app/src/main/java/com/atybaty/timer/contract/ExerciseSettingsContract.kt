package com.atybaty.timer.contract

import android.content.Context
import com.atybaty.timer.model.ExerciseGroup
import com.atybaty.timer.model.Work
import com.atybaty.timer.utils.Seconds

interface ExerciseSettingsContract {

    interface Presenter {
        enum class WorkType {
            INTERVAL, ACCELERATION
        }

        fun dialogViewCreated(exerciseGroup: ExerciseGroup, context: Context)
        fun dialogDestroyed()

        fun backButtonClicked()
        fun saveButtonClicked()
        fun workTypeSelected(workType: WorkType)

        fun exerciseDurationSet(exerciseItemPosition: Int, newDuration: Seconds, redraw: Boolean = true)
        fun exerciseIntervalDurationSet(exerciseItemPosition: Int, newDuration: Seconds, redraw: Boolean = true)
        fun exerciseAccelerationDurationSet(exerciseItemPosition: Int, newDuration: Seconds, redraw: Boolean = true)
    }

    interface View {
        fun closeDialog()
        fun showWork(work: Work)
    }
}
