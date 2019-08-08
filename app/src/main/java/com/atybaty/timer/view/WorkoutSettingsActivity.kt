package com.atybaty.timer.view

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.atybaty.timer.CurrentWorkoutHolder
import com.atybaty.timer.R
import com.atybaty.timer.view.exercisegroup.ExerciseGroupFragment

private enum class FragmentTags {
    EXERCISE_GROUP
}

class WorkoutSettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        println("activity: onCreate")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_train)

        val exerciseGroupFragment = if (savedInstanceState == null) {
            ExerciseGroupFragment().also { fragment ->
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fl_train_frames, fragment, FragmentTags.EXERCISE_GROUP.name)
                    .commit()
            }
        } else {
            supportFragmentManager.findFragmentByTag(FragmentTags.EXERCISE_GROUP.name) as ExerciseGroupFragment
        }
        // stub
        val exerciseGroup = CurrentWorkoutHolder.currentWorkout.exerciseGroups.first()
        exerciseGroupFragment.setExerciseGroup(exerciseGroup)
    }
}
