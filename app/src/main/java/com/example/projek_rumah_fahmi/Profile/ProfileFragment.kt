package com.example.projek_rumah_fahmi.Profile

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.projek_rumah_fahmi.LoginActivity
import com.example.projek_rumah_fahmi.MainActivity
import com.example.projek_rumah_fahmi.databinding.FragmentProfileBinding
import com.google.android.material.snackbar.Snackbar

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)
        val username = sharedPref.getString("username", "Fahmi") ?: "Fahmi"
        val email = sharedPref.getString("email", "fahmi@mindspace.id") ?: "fahmi@mindspace.id"

        binding.txtProfileName.text = username
        binding.txtProfileEmail.text = email

        // Settings items clicks
        binding.btnSettingReminder.setOnClickListener {
            Snackbar.make(binding.root, "Pengingat check-in harian diatur ke pukul 20:00", Snackbar.LENGTH_SHORT).show()
        }

        binding.btnSettingPrivacy.setOnClickListener {
            Snackbar.make(binding.root, "Kunci biometrik dan privasi catatan aktif", Snackbar.LENGTH_SHORT).show()
        }

        binding.btnAboutMindSpace.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Tentang MindSpace")
                .setMessage("MindSpace v1.0 • Daily Peace\nAplikasi pencatat mood & kesehatan mental harian.\nDibuat dengan arsitektur Android native.")
                .setPositiveButton("Tutup") { dialog, _ -> dialog.dismiss() }
                .show()
        }

        // Tombol Logout memanggil AlertDialog di MainActivity
        // Jika Ya -> hapus sesi dan pindah ke LoginActivity
        // Jika Tidak -> tetap di MainActivity
        binding.btnLogoutProfile.setOnClickListener {
            (activity as? MainActivity)?.showLogoutDialog()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
