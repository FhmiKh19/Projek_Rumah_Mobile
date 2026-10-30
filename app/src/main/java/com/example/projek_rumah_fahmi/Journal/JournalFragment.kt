package com.example.projek_rumah_fahmi.Journal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.projek_rumah_fahmi.MainActivity
import com.example.projek_rumah_fahmi.R
import com.example.projek_rumah_fahmi.databinding.FragmentJournalBinding
import com.google.android.material.snackbar.Snackbar

class JournalFragment : Fragment() {

    private var _binding: FragmentJournalBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJournalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Header View for ListView
        val headerView = layoutInflater.inflate(R.layout.layout_journal_header, binding.listJournalEntries, false)
        binding.listJournalEntries.addHeaderView(headerView)

        // Setup Footer View for ListView
        val footerView = layoutInflater.inflate(R.layout.layout_journal_footer, binding.listJournalEntries, false)
        binding.listJournalEntries.addFooterView(footerView)

        // Data matching Image 4 mockups
        val journalEntries = listOf(
            JournalModel(
                moodEmoji = "😌",
                moodName = "Calm",
                moodBgColor = "#EDEFFE",
                moodTextColor = "#5B6FE8",
                timeAgo = "Today, 09:30 AM",
                note = "Enjoyed a peaceful morning coffee while finishing the UI design assignment. Feeling balanced and ready for class.",
                tag1 = "☕ Coffee",
                tag2 = "🎓 University",
                tag3 = "🎨 Studio"
            ),
            JournalModel(
                moodEmoji = "😄",
                moodName = "Happy",
                moodBgColor = "#FFF8E6",
                moodTextColor = "#8A6508",
                timeAgo = "Yesterday, 08:15 PM",
                note = "Great group study session with college friends. Completed our sprint ahead of schedule.",
                tag1 = "👥 Friends",
                tag2 = "✓ Sprint Done",
                tag3 = null
            ),
            JournalModel(
                moodEmoji = "😐",
                moodName = "Okay",
                moodBgColor = "#E6F8EE",
                moodTextColor = "#1F7D4E",
                timeAgo = "23 Oct, 06:40 PM",
                note = "A busy lecture day with back-to-back presentations. Tired but glad it went smoothly.",
                tag1 = "👤 Lectures",
                tag2 = "🛌 Rest Needed",
                tag3 = null
            )
        )

        val adapter = JournalAdapter(requireContext(), journalEntries)
        binding.listJournalEntries.adapter = adapter

        // Plus button in header opens Mood Check-in
        headerView.findViewById<ImageView>(R.id.btnAddJournalEntry)?.setOnClickListener {
            (activity as? MainActivity)?.navigateToTab(R.id.nav_mood)
        }

        // Footer banner click
        footerView.findViewById<View>(R.id.cardWeeklyReflection)?.setOnClickListener {
            Snackbar.make(binding.root, "Membuka ringkasan refleksi mingguan Anda...", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
