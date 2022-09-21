package com.example.smartexpo

import android.app.AlertDialog
import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    var btnPos : Button ?= null
    var smartPoint1 : Button ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnPos=findViewById(R.id.btn_pos)
        smartPoint1=findViewById(R.id.smart_point)

        btnPos!!.setOnClickListener{
            val spAlert = View.inflate(this@MainActivity, R.layout.sp_alert, null)
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setView(spAlert)

            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

            dialog.findViewById<ImageButton>(R.id.close_btn)?.setOnClickListener{ dialog.dismiss()}
            dialog.findViewById<ImageButton>(R.id.accept_btn)?.setOnClickListener {intent = Intent(this@MainActivity, SmartPointInfo::class.java)
                startActivity(intent)
                dialog.dismiss()
            }
        }

        smartPoint1!!.setOnClickListener {
            Toast.makeText(this, "SMART POINT", Toast.LENGTH_SHORT).show()
        }
    }

}