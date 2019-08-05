package com.atybaty.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.dizitart.kno2.*
import org.dizitart.no2.objects.filters.ObjectFilters.ALL
import java.io.File

data class Hand(val hand: String)

data class Employee(val id: Long, val codeName: String, val hands: List<Hand>)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val db = nitrite {
            file = File(applicationContext.filesDir, "dbName")
            autoCommitBufferSize = 2048
            compress = true
            autoCompact = false
        }
        val repository = db.getRepository<Employee>()
        repository.remove(ALL)
        // Initialize an Object Repository
        repository.insert(Employee(1, "red", listOf(Hand("hand"), Hand("hand"))),
                Employee(2, "yellow", listOf(Hand("hand"), Hand("hand"))))

        println("start")
        println(repository.documentCollection.size())
        val cursor = repository.find()
        cursor.forEach {
            println(it)
        }

    }
}
