package com.atybaty.timer

import android.content.Context
import org.dizitart.kno2.nitrite
import org.dizitart.no2.Nitrite
import java.io.File

//https://www.dizitart.org/nitrite-database/#tips
class DBHandler {
    companion object {
        @Volatile
        private var INSTANCE: Nitrite? = null

        fun getDBInstance(context: Context): Nitrite {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildNitriteDB(context).also { INSTANCE = it }
            }
        }

        private fun buildNitriteDB(context: Context): Nitrite {
            return nitrite {
                file = File(context.filesDir, "app.db")
                autoCommitBufferSize = 2048
                compress = true
                autoCompact = false
            }
        }
    }
}