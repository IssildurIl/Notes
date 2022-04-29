package com.learning.notes.fragments.archivedTask

import com.learning.notes.fragments.ShowTaskFragment


class ArchivedTasks : ShowTaskFragment() {

    override fun getObservable() = taskViewModel.readArchived
    override fun showFab(): Boolean = false
}