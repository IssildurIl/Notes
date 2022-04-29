package com.learning.notes.activitiy

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.learning.notes.R
import com.learning.notes.viewmodel.NotesViewModel

abstract class BaseActivity : AppCompatActivity() {

    internal abstract val model: NotesViewModel
    internal abstract val binding: ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.root.isSaveFromParentEnabled = false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuId = R.menu.navigation

        menuInflater.inflate(menuId, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
            R.id.Archived -> archiveNote()
            R.id.Notes -> note()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun archiveNote() {
        model.moveToArchive()
        onBackPressed()
    }

    private fun note() {
        model.moveToActive()
        onBackPressed()
    }

}