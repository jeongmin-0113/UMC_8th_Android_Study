package com.example.week1_workbook

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val yellowStamp = findViewById<ImageView>(R.id.img_yellowStamp)
        yellowStamp.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }

        val blueStamp = findViewById<ImageView>(R.id.img_blueStamp)
        blueStamp.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }

        val frameLayout = findViewById<FrameLayout>(R.id.frame_layout)

        val purpleStamp = findViewById<ImageView>(R.id.img_purpleStamp)
        purpleStamp.setOnClickListener {
            frameLayout.setBackgroundColor(R.color.purple)
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frame_layout, DetailFragment())
            fragmentTransaction.commit()
        }

        val greenStamp = findViewById<ImageView>(R.id.img_greenStamp)
        greenStamp.setOnClickListener {
            frameLayout.setBackgroundColor(R.color.green)
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frame_layout, DetailFragment())
            fragmentTransaction.commit()
        }

        val redStamp = findViewById<ImageView>(R.id.img_redStamp)
        redStamp.setOnClickListener {
            frameLayout.setBackgroundColor(R.color.red)
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frame_layout, DetailFragment())
            fragmentTransaction.commit()
        }
    }
}