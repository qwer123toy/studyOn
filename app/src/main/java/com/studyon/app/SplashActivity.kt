package com.studyon.app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.studyon.app.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivitySplashBinding
    private val splashTimeOut: Long = 2000 // 2초

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            binding = ActivitySplashBinding.inflate(layoutInflater)
            setContentView(binding.root)

            // ActionBar 숨기기
            supportActionBar?.hide()

            // 5초 후 메인 액티비티로 이동 (테스트용으로 시간 연장)
            Handler(Looper.getMainLooper()).postDelayed({
                try {
                    Log.d("SplashActivity", "Starting MainActivity")
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } catch (e: Exception) {
                    Log.e("SplashActivity", "Error starting MainActivity", e)
                    // MainActivity 시작 실패 시 앱 종료하지 않고 스플래시에서 대기
                }
            }, 5000) // 5초로 연장
        } catch (e: Exception) {
            Log.e("SplashActivity", "Error in onCreate", e)
        }
    }
} 