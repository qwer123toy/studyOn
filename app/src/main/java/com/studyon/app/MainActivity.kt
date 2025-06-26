package com.studyon.app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.studyon.app.data.preferences.AppSettings
import com.studyon.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private lateinit var appSettings: AppSettings

    override fun onCreate(savedInstanceState: Bundle?) {
        // 테마 설정을 먼저 적용
        appSettings = AppSettings(this)
        applyTheme()
        
        super.onCreate(savedInstanceState)
        try {
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            setupNavigation()
        } catch (e: Exception) {
            Log.e("MainActivity", "Error in onCreate", e)
        }
    }

    private fun applyTheme() {
        when (appSettings.themeMode) {
            "light" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            "dark" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) // 기본은 밝은 테마
        }
    }

    private fun setupNavigation() {
        try {
            // ActionBar 숨기기
            supportActionBar?.hide()
            
            val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
            
            if (navHostFragment == null) {
                Log.e("MainActivity", "NavHostFragment not found")
                return
            }
            
            val navController = navHostFragment.navController
            
            // BottomNavigationView와 NavController 연결
            binding.bottomNavigation.setupWithNavController(navController)
            
            // 홈 버튼 클릭 시 백스택 정리하고 홈으로 이동
            binding.bottomNavigation.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.navigation_home -> {
                        // 홈으로 이동 시 백스택 정리
                        navController.popBackStack(R.id.navigation_home, false)
                        true
                    }
                    else -> {
                        // 다른 메뉴는 기본 동작
                        navController.navigate(item.itemId)
                        true
                    }
                }
            }
            
            Log.d("MainActivity", "Navigation setup completed")
        } catch (e: Exception) {
            Log.e("MainActivity", "Error setting up navigation", e)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return try {
            val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
            navHostFragment?.navController?.navigateUp() ?: super.onSupportNavigateUp()
        } catch (e: Exception) {
            Log.e("MainActivity", "Error in onSupportNavigateUp", e)
            super.onSupportNavigateUp()
        }
    }
} 