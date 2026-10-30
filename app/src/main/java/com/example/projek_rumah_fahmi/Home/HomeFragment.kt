package com.example.projek_rumah_fahmi.Home

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projek_rumah_fahmi.MainActivity
import com.example.projek_rumah_fahmi.R
import com.example.projek_rumah_fahmi.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)
        val username = sharedPref.getString("username", "Fahmi") ?: "Fahmi"
        binding.txtGreetingName.text = "$username 👋"

        // Quick mood pick reactions
        binding.cardMoodHappy.setOnClickListener {
            Snackbar.make(binding.root, "Senang melihatmu bahagia hari ini! 😄", Snackbar.LENGTH_SHORT).show()
        }

        binding.cardMoodCalm.setOnClickListener {
            Snackbar.make(binding.root, "Ketenangan adalah kunci kedamaian batin. 😌", Snackbar.LENGTH_SHORT).show()
        }

        binding.cardMoodOkay.setOnClickListener {
            Snackbar.make(binding.root, "Hari yang tenang dan seimbang. Tetap semangat! 😐", Snackbar.LENGTH_SHORT).show()
        }

        binding.cardMoodSad.setOnClickListener {
            Snackbar.make(binding.root, "Tidak apa-apa merasa sedih. Ambil jeda sejenak ya. 😔", Snackbar.LENGTH_SHORT).show()
        }

        binding.cardMoodAnxious.setOnClickListener {
            Snackbar.make(binding.root, "Tarik napas dalam-dalam. Semua akan baik-baik saja. 😰", Snackbar.LENGTH_SHORT).show()
        }

        // Navigasi ke Tab Mood Check-in
        binding.btnCheckInHome.setOnClickListener {
            (activity as? MainActivity)?.navigateToTab(R.id.nav_mood)
        }

        binding.txtSelectOne.setOnClickListener {
            (activity as? MainActivity)?.navigateToTab(R.id.nav_mood)
        }

        // Navigasi ke Tab Journal / History
        binding.txtHistoryLink.setOnClickListener {
            (activity as? MainActivity)?.navigateToTab(R.id.nav_journal)
        }

        // Breathing pacer play click
        binding.btnPlayBreathing.setOnClickListener {
            Snackbar.make(binding.root, "Memulai panduan pernapasan 3 menit...", Snackbar.LENGTH_LONG)
                .setAction("MULAI") {
                    Snackbar.make(binding.root, "Tarik napas... 1.. 2.. 3.. 4.. Hembuskan...", Snackbar.LENGTH_SHORT).show()
                }
                .show()
        }

        binding.btnQuickAlert.setOnClickListener {
            Snackbar.make(binding.root, "Pengingat check-in harian Anda aktif pada pukul 20:00", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
