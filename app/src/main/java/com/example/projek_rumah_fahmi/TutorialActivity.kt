package com.example.projek_rumah_fahmi

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.projek_rumah_fahmi.databinding.ActivityTutorialBinding

class TutorialActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTutorialBinding

    data class SlideData(
        val emoji: String,
        val title: String,
        val desc: String,
        val chip1: String,
        val chip2: String
    )

    private val slides = listOf(
        SlideData(
            emoji = "🧠",
            title = "Selamat Datang di MindSpace",
            desc = "Tempat aman untuk mencatat perasaan dan merawat kesehatan mental harianmu.",
            chip1 = "✨ Gratis",
            chip2 = "🔒 Privat"
        ),
        SlideData(
            emoji = "📓",
            title = "Catat Jurnal Harianmu",
            desc = "Tulis perasaan, pikiran, dan pengalamanmu setiap hari untuk memahami dirimu lebih baik.",
            chip1 = "📝 Jurnal",
            chip2 = "😊 Mood"
        ),
        SlideData(
            emoji = "🌿",
            title = "Mulai Perjalananmu",
            desc = "Bangun kebiasaan check-in harian dan lihat progres kesehatan mentalmu dari waktu ke waktu.",
            chip1 = "🔥 Streak",
            chip2 = "📊 Statistik"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup ViewPager2 adapter
        binding.viewPagerTutorial.adapter = TutorialPagerAdapter(slides)

        // Hubungkan WormDotsIndicator otomatis ke ViewPager2 (Pola P8)
        binding.dotIndicator.attachTo(binding.viewPagerTutorial)

        // Update tampilan tombol saat slide berganti
        binding.viewPagerTutorial.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateButton(position)
            }
        })

        // Tombol Next / Mulai
        binding.btnTutorialNext.setOnClickListener {
            val current = binding.viewPagerTutorial.currentItem
            if (current < slides.size - 1) {
                binding.viewPagerTutorial.currentItem = current + 1
            } else {
                finishTutorial()
            }
        }

        // Tombol Skip
        binding.btnTutorialSkip.setOnClickListener {
            finishTutorial()
        }

        // Tombol Back di pojok atas
        binding.btnTutorialBack.setOnClickListener {
            finishTutorial()
        }

        // Set state awal tombol
        updateButton(0)
    }

    private fun updateButton(position: Int) {
        if (position == slides.size - 1) {
            binding.btnTutorialNext.text = "Mulai Sekarang 🚀"
            binding.btnTutorialSkip.visibility = View.GONE
        } else {
            binding.btnTutorialNext.text = "Selanjutnya →"
            binding.btnTutorialSkip.visibility = View.VISIBLE
        }
    }

    private fun finishTutorial() {
        getSharedPreferences("app_pref", MODE_PRIVATE)
            .edit()
            .putBoolean("tutorial_done", true)
            .apply()

        val isLogin = getSharedPreferences("user_pref", MODE_PRIVATE).getBoolean("isLogin", false)
        if (!isLogin) {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        finish()
    }

    // ─── ViewPager2 Adapter

    inner class TutorialPagerAdapter(private val data: List<SlideData>) :
        RecyclerView.Adapter<TutorialPagerAdapter.SlideViewHolder>() {

        inner class SlideViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val emoji: TextView = itemView.findViewById(R.id.txtSlideEmoji)
            val title: TextView = itemView.findViewById(R.id.txtSlideTitle)
            val desc: TextView = itemView.findViewById(R.id.txtSlideDesc)
            val chip1: TextView = itemView.findViewById(R.id.chipFeature1)
            val chip2: TextView = itemView.findViewById(R.id.chipFeature2)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlideViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_tutorial_slide, parent, false)
            return SlideViewHolder(view)
        }

        override fun onBindViewHolder(holder: SlideViewHolder, position: Int) {
            val slide = data[position]
            holder.emoji.text = slide.emoji
            holder.title.text = slide.title
            holder.desc.text = slide.desc
            holder.chip1.text = slide.chip1
            holder.chip2.text = slide.chip2
        }

        override fun getItemCount() = data.size
    }
}
