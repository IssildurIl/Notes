package com.learning.notes.models

import android.text.TextUtils
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val header: String,
    val body: String,
    val timestamp: Long
) {
    fun isEmptyHeader(): Boolean {
        return TextUtils.isEmpty(this.header)
    }

    fun isEmptyBody(): Boolean {
        return TextUtils.isEmpty(this.body)
    }
}



