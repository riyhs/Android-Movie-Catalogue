package com.riyaldi.moviecatalogue.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.riyaldi.moviecatalogue.R
import com.riyaldi.moviecatalogue.ui.home.HomeActivity

class SplashActivity : AppCompatActivity() {

    companion object {
        private const val TWO_SECOND_IN_MILLIS = 2000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }, TWO_SECOND_IN_MILLIS)
    }
}