package com.learning.notes.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.learning.notes.model.enums.TaskStatus
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    var header: String,
    val body: String,
    val timestamp: Long,
    var status: TaskStatus
) : Parcelable {
}



