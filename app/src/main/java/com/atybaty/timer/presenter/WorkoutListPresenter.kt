package com.atybaty.timer.presenter

import com.atybaty.timer.contract.IWorkoutListContract
import com.atybaty.timer.view.WorkoutListActivity
import org.dizitart.no2.Nitrite

class WorkoutListPresenter(private val view: WorkoutListActivity): IWorkoutListContract.Presenter {
    override fun activityCreated(dbInstance: Nitrite) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addButtonClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteButtonClicked(itemPosition: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun playButtonClicked(itemPosition: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun itemClicked(itemPosition: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun activityDestroyed() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun onDestroy() {

    }
}
