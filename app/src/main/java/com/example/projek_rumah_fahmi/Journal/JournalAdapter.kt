package com.example.projek_rumah_fahmi.Journal

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.projek_rumah_fahmi.databinding.ItemJournalBinding
import com.google.android.material.snackbar.Snackbar

class JournalAdapter(
    context: Context,
    private val entries: List<JournalModel>
) : ArrayAdapter<JournalModel>(context, 0, entries) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemJournalBinding.inflate(LayoutInflater.from(context), parent, false)
        val data = entries[position]

        binding.txtItemEmoji.text = data.moodEmoji
        binding.txtItemMood.text = data.moodName
        binding.txtItemMood.setTextColor(Color.parseColor(data.moodTextColor))
        binding.layoutMoodBadge.backgroundTintList = android.content.res.ColorStateList.valueOf(Color.parseColor(data.moodBgColor))

        binding.txtItemTime.text = data.timeAgo
        binding.txtItemNote.text = data.note

        binding.txtTag1.text = data.tag1
        binding.txtTag2.text = data.tag2
        if (data.tag3 != null) {
            binding.txtTag3.visibility = View.VISIBLE
            binding.txtTag3.text = data.tag3
        } else {
            binding.txtTag3.visibility = View.GONE
        }

        binding.root.setOnClickListener {
            val intent = android.content.Intent(context, com.example.projek_rumah_fahmi.DetailActivity::class.java)
            intent.putExtra("SENDER", data.moodName)
            intent.putExtra("PESAN", data.note)
            intent.putExtra("EMOJI", data.moodEmoji)
            intent.putExtra("TIME", data.timeAgo)
            val tags = "${data.tag1}  •  ${data.tag2}${if (data.tag3 != null) "  •  " + data.tag3 else ""}"
            intent.putExtra("TAGS", tags)
            context.startActivity(intent)
        }

        binding.txtItemMenuDots.setOnClickListener {
            Snackbar.make(binding.root, "Opsi untuk entri ${data.moodName}", Snackbar.LENGTH_SHORT).show()
        }

        return binding.root
    }
}
