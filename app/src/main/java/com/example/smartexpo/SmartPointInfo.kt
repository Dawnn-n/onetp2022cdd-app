package com.example.smartexpo

import android.content.ContentValues
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SmartPointInfo : AppCompatActivity() {
    private val db = Firebase.firestore
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
        setContentView(R.layout.sp_info)
        var txtView = findViewById<TextView>(R.id.sp_info_txt)
        val docRef = db.collection("smart_point").document("smart_point1")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val text = document.getString("content")
                    txtView.text = text
                } else {
                    Log.d(ContentValues.TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }

        mp.start()

        findViewById<ImageButton>(R.id.close_btn)?.setOnClickListener{
            intent = Intent(this@SmartPointInfo, MainActivity::class.java)
            startActivity(intent)
            mp.pause()
            mp.seekTo(0)
        }
        findViewById<ImageButton>(R.id.replay_btn)?.setOnClickListener{
            mp.seekTo(0)
        }



    }
}