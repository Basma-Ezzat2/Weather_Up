package com.example.weatherup.ui.activities

import android.content.Intent

import android.os.Bundle
import android.os.Handler

import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity

import com.example.weatherup.R


class Splash : AppCompatActivity() {

    lateinit var  image:ImageView
    lateinit var  titleTv:TextView
    lateinit var  subTitle:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        image=findViewById(R.id.image)
        titleTv=findViewById(R.id.titleTv)
        subTitle=findViewById(R.id.subTitle)
        val topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        val bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        image.startAnimation(topAnimation)
        titleTv.startAnimation(bottomAnimation)
        subTitle.startAnimation(bottomAnimation)
        supportActionBar?.hide()
        val splashTimeOut = 2500
        val intent = Intent(this@Splash, MainActivity::class.java)
        Handler().postDelayed({
            startActivity(intent)
            finish()
        }, splashTimeOut.toLong())

    }
}