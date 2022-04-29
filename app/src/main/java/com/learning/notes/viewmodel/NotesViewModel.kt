package com.learning.notes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.learning.notes.model.enums.TaskStatus

abstract class NotesViewModel(app: Application) : AndroidViewModel(app) {

    var status = TaskStatus.ACTIVE

    fun moveToActive() {
        status = TaskStatus.ACTIVE
    }

    fun moveToArchive() {
        status = TaskStatus.ARCHIVED
    }


}