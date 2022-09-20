package com.example.smartexpo

import android.app.AlertDialog
import android.app.Dialog
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    val db = Firebase.firestore
    var btn_pos : Button ?= null
    var smart_point_1 : Button ?= null
    val fd by lazy {
        assets.openFd("sp_info_audio.mp3")
    }
    val mp by lazy{
        val m = MediaPlayer()
        m.setDataSource(
            fd.fileDescriptor,
            fd.startOffset,
            fd.length
        )
        fd.close()
        m.prepare()
        m
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_pos=findViewById(R.id.btn_pos)
        smart_point_1=findViewById(R.id.smart_point)

        btn_pos!!.setOnClickListener{
            val sp_alert = View.inflate(this@MainActivity, R.layout.sp_alert, null)
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setView(sp_alert)

            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

            dialog.findViewById<ImageButton>(R.id.close_btn)?.setOnClickListener{ dialog.dismiss()}
            dialog.findViewById<ImageButton>(R.id.accept_btn)?.setOnClickListener {
                dialog.dismiss()
                val sp_info = View.inflate(this@MainActivity, R.layout.sp_info,null)
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setView(sp_info)

                val dialog = builder.create()
                dialog.show()
                dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

                mp.start()

                dialog.findViewById<ImageButton>(R.id.close_btn)?.setOnClickListener{
                dialog.dismiss()
                mp.pause()
                mp.seekTo(0)
                }
                dialog.findViewById<ImageButton>(R.id.replay_btn)?.setOnClickListener{
                    mp.seekTo(0)
                }
            }
        }

        smart_point_1!!.setOnClickListener {
            Toast.makeText(this, "SMART POINT", Toast.LENGTH_SHORT).show()
        }
    }

    /*private fun setUsers(){
         Create a new user with a first and last name
        val user = hashMapOf(
            "first" to "Ada",
            "last" to "Lovelace",
            "born" to 1815
        )
         Add a new document with a generated ID
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }

    }*/

}