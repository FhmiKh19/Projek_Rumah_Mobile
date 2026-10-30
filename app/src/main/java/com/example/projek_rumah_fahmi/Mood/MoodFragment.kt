package com.example.projek_rumah_fahmi.Mood

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.projek_rumah_fahmi.MainActivity
import com.example.projek_rumah_fahmi.R
import com.example.projek_rumah_fahmi.databinding.FragmentMoodBinding
import com.google.android.material.snackbar.Snackbar

class MoodFragment : Fragment() {

    private var _binding: FragmentMoodBinding? = null
    private val binding get() = _binding!!

    private var selectedMood = "Calm"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Mood Selection
        binding.cardOptionHappy.setOnClickListener { selectMood("Happy") }
        binding.cardOptionCalm.setOnClickListener { selectMood("Calm") }
        binding.cardOptionOkay.setOnClickListener { selectMood("Okay") }
        binding.cardOptionSad.setOnClickListener { selectMood("Sad") }
        binding.cardOptionAnxious.setOnClickListener { selectMood("Anxious") }

        // Setup Tag Chips Toggle
        val chips = listOf(
            binding.chipAcademics, binding.chipRest, binding.chipFitness,
            binding.chipFriends, binding.chipNature, binding.chipSleep
        )
        for (chip in chips) {
            chip.setOnClickListener {
                chip.isSelected = !chip.isSelected
                if (chip.isSelected) {
                    chip.setBackgroundResource(R.drawable.bg_circle_purple)
                    chip.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.primary)
                    chip.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                } else {
                    chip.setBackgroundResource(R.drawable.bg_chip)
                    chip.backgroundTintList = null
                    chip.setTextColor(ContextCompat.getColor(requireContext(), R.color.tag_text))
                }
            }
        }

        // TextWatcher for Character Count
        binding.edtThoughts.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val len = s?.length ?: 0
                binding.txtCharCount.text = "$len / 280"
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        // Save Button Click
        binding.btnSaveMood.setOnClickListener {
            val thoughts = binding.edtThoughts.text.toString().trim()
            val message = if (thoughts.isNotEmpty()) {
                "Mood ($selectedMood) dan catatan berhasil disimpan! ✨"
            } else {
                "Mood ($selectedMood) berhasil dicatat ke grafik mingguan! ✨"
            }

            Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG)
                .setAction("LIHAT JURNAL") {
                    (activity as? MainActivity)?.navigateToTab(R.id.nav_journal)
                }
                .show()
        }

        // Back Button to Home
        binding.btnBackMood.setOnClickListener {
            (activity as? MainActivity)?.navigateToTab(R.id.nav_home)
        }
    }

    private fun selectMood(mood: String) {
        selectedMood = mood
        val primaryColor = ContextCompat.getColor(requireContext(), R.color.primary)
        val defaultBorderColor = ContextCompat.getColor(requireContext(), R.color.card_border)

        // Reset all borders
        binding.cardOptionHappy.strokeColor = ContextCompat.getColor(requireContext(), R.color.mood_happy_border)
        binding.cardOptionHappy.strokeWidth = 2
        binding.cardOptionCalm.strokeColor = defaultBorderColor
        binding.cardOptionCalm.strokeWidth = 2
        binding.cardOptionOkay.strokeColor = ContextCompat.getColor(requireContext(), R.color.mood_okay_border)
        binding.cardOptionOkay.strokeWidth = 2
        binding.cardOptionSad.strokeColor = ContextCompat.getColor(requireContext(), R.color.mood_sad_border)
        binding.cardOptionSad.strokeWidth = 2
        binding.cardOptionAnxious.strokeColor = ContextCompat.getColor(requireContext(), R.color.mood_anxious_border)
        binding.cardOptionAnxious.strokeWidth = 2

        // Highlight selected
        when (mood) {
            "Happy" -> {
                binding.cardOptionHappy.strokeColor = primaryColor
                binding.cardOptionHappy.strokeWidth = 5
            }
            "Calm" -> {
                binding.cardOptionCalm.strokeColor = primaryColor
                binding.cardOptionCalm.strokeWidth = 5
            }
            "Okay" -> {
                binding.cardOptionOkay.strokeColor = primaryColor
                binding.cardOptionOkay.strokeWidth = 5
            }
            "Sad" -> {
                binding.cardOptionSad.strokeColor = primaryColor
                binding.cardOptionSad.strokeWidth = 5
            }
            "Anxious" -> {
                binding.cardOptionAnxious.strokeColor = primaryColor
                binding.cardOptionAnxious.strokeWidth = 5
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
