package com.atybaty.timer.model

import android.content.Context
import com.atybaty.timer.R
import com.atybaty.timer.util.Seconds
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
sealed class Exercise(var duration: Seconds) {
    abstract fun getName(context: Context): String

    abstract fun deepCopy(): Exercise
}

class Work(var name: String, duration: Seconds, var options: WorkOptions) : Exercise(duration) {
    override fun getName(context: Context) = name

    override fun deepCopy(): Exercise {
        return Work(name, duration, options.copy())
    }
}

abstract class Relaxation(private val nameStringId: Int, duration: Seconds) : Exercise(duration) {
    override fun getName(context: Context) = context.getString(nameStringId)
}


class RunUp(duration: Seconds) : Relaxation(R.string.exercises_run_up, duration){

    override fun deepCopy(): Exercise {
        return RunUp(this.duration)
    }
}

class RestBetweenSets(duration: Seconds) : Relaxation(R.string.exercises_rest_between_sets, duration){

    override fun deepCopy(): Exercise {
        return RestBetweenSets(this.duration)
    }
}

class CalmDown(duration: Seconds) : Relaxation(R.string.exercises_calm_down, duration){

    override fun deepCopy(): Exercise {
        return CalmDown(this.duration)
    }
}


@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
sealed class WorkOptions{
    abstract fun copy(): WorkOptions
}

object SimpleWorkOptions : WorkOptions(){
    override fun copy(): WorkOptions {
        return SimpleWorkOptions
    }
}

data class WorkWithAccelerationOptions(var accelerationDuration: Seconds) : WorkOptions(){

    override fun copy(): WorkOptions {
        return WorkWithAccelerationOptions(this.accelerationDuration)
    }
}

data class WorkWithIntervalsOptions(var interval: Seconds, var rattle: Seconds) : WorkOptions(){

    override fun copy(): WorkOptions {
        return WorkWithIntervalsOptions(this.interval, this.rattle)
    }
}
