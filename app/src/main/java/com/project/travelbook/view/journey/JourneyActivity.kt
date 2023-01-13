package com.project.travelbook.view.journey

import android.content.DialogInterface
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.travelbook.R
import com.project.travelbook.model.ModelDatabase
import com.project.travelbook.view.journey.JourneyAdapter.HistoryAdapterCallback
import com.project.travelbook.viewmodel.JourneyViewModel
import kotlinx.android.synthetic.main.activity_journey.*

class JourneyActivity : AppCompatActivity(), HistoryAdapterCallback {
    var modelDatabaseList: MutableList<ModelDatabase> = ArrayList()
    lateinit var journeyAdapter: JourneyAdapter
    lateinit var journeyViewModel: JourneyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_journey)

        setInitLayout()
        setViewModel()
    }

    private fun setInitLayout() {
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }

        tvNotFound.visibility = View.GONE

        journeyAdapter = JourneyAdapter(this, modelDatabaseList, this)
        rvHistory.setHasFixedSize(true)
        rvHistory.layoutManager = LinearLayoutManager(this)
        rvHistory.adapter = journeyAdapter
    }

    private fun setViewModel() {
        journeyViewModel = ViewModelProviders.of(this).get(JourneyViewModel::class.java)
        journeyViewModel.dataLaporan.observe(this) { modelDatabases: List<ModelDatabase> ->
            if (modelDatabases.isEmpty()) {
                tvNotFound.visibility = View.VISIBLE
                rvHistory.visibility = View.GONE
            } else {
                tvNotFound.visibility = View.GONE
                rvHistory.visibility = View.VISIBLE
            }
            journeyAdapter.setDataAdapter(modelDatabases)
        }
    }

    override fun onDelete(modelDatabase: ModelDatabase?) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setMessage("Delete this data?")
        alertDialogBuilder.setPositiveButton("Yes") { dialogInterface, i ->
            val uid = modelDatabase!!.uid
            journeyViewModel.deleteDataById(uid)
            Toast.makeText(this@JourneyActivity, "Data deleted",
                Toast.LENGTH_SHORT).show()
        }
        alertDialogBuilder.setNegativeButton("Cancel") { dialogInterface: DialogInterface, i:
        Int -> dialogInterface.cancel() }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}