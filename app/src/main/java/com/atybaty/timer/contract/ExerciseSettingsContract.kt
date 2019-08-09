package com.atybaty.timer.contract

import android.content.Context
import com.atybaty.timer.model.Work
import com.atybaty.timer.utils.Seconds

interface ExerciseSettingsContract {

    interface Presenter {
        enum class WorkType {
            INTERVAL, ACCELERATION
        }

        fun dialogViewCreated(context: Context)
        fun dialogDismissed(listener: () -> Unit)

        fun backButtonClicked()
        fun saveButtonClicked()
        fun workTypeSelected(workType: WorkType)

        fun exerciseNameSet(newName: String)
        fun exerciseDurationSet(newDuration: Seconds)
        fun exerciseIntervalDurationSet(newDuration: Seconds)
        fun exerciseAccelerationDurationSet(newDuration: Seconds)
    }

    interface View {
        fun closeDialog()
        fun showWork(work: Work)
    }
}
