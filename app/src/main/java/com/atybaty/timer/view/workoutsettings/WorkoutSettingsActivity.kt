package com.atybaty.timer.view.workoutsettings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.atybaty.timer.dataholders.CurrentWorkoutHolder
import com.atybaty.timer.R
import com.atybaty.timer.view.workout.WorkoutFragment
import com.atybaty.timer.view.workoutsettings.FragmentTag.EXERCISE_GROUP
import com.atybaty.timer.view.workoutsettings.exercisegroup.ExerciseGroupFragment

private enum class FragmentTag {
    EXERCISE_GROUP, WORKOUT,
}

class WorkoutSettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_train)

        loadFragment(savedInstanceState, FragmentTag.WORKOUT) {
            WorkoutFragment()
        }
        // TODO: stub
        val exerciseGroup = CurrentWorkoutHolder.currentWorkout.exerciseGroups.first()
        CurrentWorkoutHolder.currentExerciseGroup = exerciseGroup
    }

    private inline fun <reified F: Fragment> loadFragment(savedInstanceState: Bundle?, tag: FragmentTag, builder: () -> F) : F {
        return if (savedInstanceState == null) {
            builder().also { fragment ->
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fl_train_frames, fragment, tag.name)
                    .commit()
            }
        } else {
            supportFragmentManager.findFragmentByTag(tag.name)!! as F
        }
    }
}
