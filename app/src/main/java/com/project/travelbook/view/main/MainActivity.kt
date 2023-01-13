package com.project.travelbook.view.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.project.travelbook.R
import com.project.travelbook.utils.SessionLogin
import com.project.travelbook.view.travel.TravelActivity
import com.project.travelbook.view.journey.JourneyActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var strTitle: String
    lateinit var session: SessionLogin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setInitLayout()
    }

    private fun setInitLayout() {
        session = SessionLogin(this)
        session.checkLogin()

        cvCaptureScenery.setOnClickListener {
            strTitle = "Capture Scenery"
            val intent = Intent(this@MainActivity, TravelActivity::class.java)
            intent.putExtra(TravelActivity.DATA_TITLE, strTitle)
            startActivity(intent)
        }

        cvCaptureSelfie.setOnClickListener {
            strTitle = "Capture Selfie"
            val intent = Intent(this@MainActivity, TravelActivity::class.java)
            intent.putExtra(TravelActivity.DATA_TITLE, strTitle)
            startActivity(intent)
        }

        cvHistory.setOnClickListener {
            val intent = Intent(this@MainActivity, JourneyActivity::class.java)
            startActivity(intent)
        }

        imageLogout.setOnClickListener {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setMessage("Logout?")
            builder.setCancelable(true)
            builder.setNegativeButton("Cancel") { dialog, which -> dialog.cancel() }
            builder.setPositiveButton("Yes") { dialog, which ->
                session.logoutUser()
                finishAffinity()
            }
            val alertDialog = builder.create()
            alertDialog.show()
        }

//        bottom_nav.setOnItemSelectedListener{
//
//            fun builder() = AlertDialog.Builder(this@MainActivity)
//
//            when(it.itemId){
//                R.id.log_out ->
//                    builder().setMessage("Logout?")
//                    builder().setCancelable(true)
//                    builder().setNegativeButton("Cancel"){dialog, which -> dialog.cancel()}
//                    builder().setPositiveButton("Yes"){dialog, which ->
//                        session.logoutUser()
//                        finishAffinity()
//                    }
//
//
//            }
//
//
////            builder.setMessage("Logout?")
////            builder.setCancelable(true)
////            builder.setNegativeButton("Cancel") { dialog, which -> dialog.cancel() }
////            builder.setPositiveButton("Yes") { dialog, which ->
////                session.logoutUser()
////                finishAffinity()
////            }
//
//            val alertDialog = builder.create()
//            alertDialog.show()
//        }

    }

}


