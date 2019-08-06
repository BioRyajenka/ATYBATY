package com.atybaty.timer

import android.content.Context
import com.atybaty.timer.model.repository.WorkoutRepository
import org.dizitart.kno2.nitrite
import org.dizitart.no2.Nitrite
import java.io.File

private const val DB_FILE_NAME = "app.db"

// see https://www.dizitart.org/nitrite-database/#tips
class WorkoutRepositoryHolder {
    companion object {
        fun getWorkoutRepository(context: Context): WorkoutRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: WorkoutRepository(buildNitriteDB(context)).also {
                    INSTANCE = it
                }
            }
        }

        @Volatile
        private var INSTANCE: WorkoutRepository? = null

        private fun buildNitriteDB(context: Context): Nitrite {
            return nitrite {
                file = File(context.filesDir, DB_FILE_NAME)
                autoCommitBufferSize = 2048
                compress = true
                autoCompact = false
            }
        }
    }
}
