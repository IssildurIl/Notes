package com.learning.notes.fragments.taskList

import com.learning.notes.fragments.ShowTaskFragment


class TaskList :ShowTaskFragment() {

    override fun getObservable() = taskViewModel.readActive
    override fun showFab(): Boolean = true

}