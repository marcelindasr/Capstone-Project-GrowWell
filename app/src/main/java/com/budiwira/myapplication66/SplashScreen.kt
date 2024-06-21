package com.budiwira.myapplication66
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {
    private val splashScreenDuration: Long = 3000 // 3 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            // Start the LoginActivity
            startActivity(Intent(this@SplashScreen, LoginActivity::class.java))
            // Finish the splash screen activity
            finish()
        }, splashScreenDuration)
    }
}
