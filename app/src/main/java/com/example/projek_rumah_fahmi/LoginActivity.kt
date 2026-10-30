package com.example.projek_rumah_fahmi

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projek_rumah_fahmi.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        // Toggle Password Visibility
        binding.imgTogglePassword.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            if (isPasswordVisible) {
                binding.edtPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                binding.edtPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            }
            binding.edtPassword.setSelection(binding.edtPassword.text.length)
        }

        // Login Button Click
        binding.btnLogIn.setOnClickListener {
            val email = binding.edtEmail.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Simpan status login & username ke SharedPreferences sesuai pola praktikum
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("email", email)
                editor.putString("username", "Fahmi")
                editor.apply()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                AlertDialog.Builder(this)
                    .setTitle("Login Gagal")
                    .setMessage("Silakan masukkan email dan password terlebih dahulu.")
                    .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                    .show()
            }
        }

        // Quick Breathe Action
        binding.btnQuickBreathe.setOnClickListener {
            Snackbar.make(binding.root, "Tarik napas perlahan... tahan 4 detik... hembuskan...", Snackbar.LENGTH_LONG)
                .setAction("OKE", null)
                .show()
        }

        // Social Logins (Google & Apple)
        binding.btnGoogle.setOnClickListener {
            Snackbar.make(binding.root, "Fitur masuk dengan Google sedang disiapkan", Snackbar.LENGTH_SHORT).show()
        }

        binding.btnApple.setOnClickListener {
            Snackbar.make(binding.root, "Fitur masuk dengan Apple sedang disiapkan", Snackbar.LENGTH_SHORT).show()
        }

        binding.txtForgotPassword.setOnClickListener {
            Snackbar.make(binding.root, "Tautan reset kata sandi telah dikirim ke email Anda", Snackbar.LENGTH_SHORT).show()
        }

        binding.txtSignUp.setOnClickListener {
            Snackbar.make(binding.root, "Form pendaftaran akun baru akan segera hadir!", Snackbar.LENGTH_SHORT).show()
        }
    }
}
