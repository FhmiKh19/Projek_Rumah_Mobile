package com.example.projek_rumah_fahmi

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.projek_rumah_fahmi.Home.HomeFragment
import com.example.projek_rumah_fahmi.Journal.JournalFragment
import com.example.projek_rumah_fahmi.Mood.MoodFragment
import com.example.projek_rumah_fahmi.Profile.ProfileFragment
import com.example.projek_rumah_fahmi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        // Default tampilkan HomeFragment
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        // Listener pergantian tab BottomNavigationView
        binding.bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.nav_mood -> {
                    replaceFragment(MoodFragment())
                    true
                }
                R.id.nav_journal -> {
                    replaceFragment(JournalFragment())
                    true
                }
                R.id.nav_profile -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    // Fungsi ganti fragment sesuai yang dipelajari di praktikum
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }

    // Helper navigasi antar tab
    fun navigateToTab(menuId: Int) {
        binding.bottomNavView.selectedItemId = menuId
    }

    // Penerapan AlertDialog Logout pada MainActivity
    // Pilihan "Ya" -> pindah ke LoginActivity dan hapus sesi
    // Pilihan "Tidak" -> tetap berada di halaman MainActivity
    fun showLogoutDialog() {
        AlertDialog.Builder(this)
            .setTitle("Log Out")
            .setMessage("Apakah Anda yakin ingin keluar dari aplikasi?")
            .setPositiveButton("Ya") { dialog, _ ->
                val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.clear()
                editor.apply()

                dialog.dismiss()

                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            .setNegativeButton("Tidak") { dialog, _ ->
                // Jika "Tidak" maka dialog ditutup dan tetap berada di MainActivity
                dialog.dismiss()
            }
            .setCancelable(true)
            .show()
    }
}
