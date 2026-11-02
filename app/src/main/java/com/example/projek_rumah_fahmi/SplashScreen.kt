package com.example.projek_rumah_fahmi

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val userPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val appPref  = getSharedPreferences("app_pref",  MODE_PRIVATE)

        val isLogin       = userPref.getBoolean("isLogin",       false)
        val tutorialDone  = appPref.getBoolean("tutorial_done",  false)

        lifecycleScope.launch {
            delay(2000) // Delay 2 detik sebelum masuk ke halaman berikutnya
            val destination = when {
                isLogin       -> Intent(this@SplashScreen, MainActivity::class.java)
                tutorialDone  -> Intent(this@SplashScreen, LoginActivity::class.java)
                else          -> Intent(this@SplashScreen, TutorialActivity::class.java)
            }
            startActivity(destination)
            finish()
        }
    }
}
