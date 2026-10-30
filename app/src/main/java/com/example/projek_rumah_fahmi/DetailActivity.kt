package com.example.projek_rumah_fahmi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projek_rumah_fahmi.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Ambil data dari Intent persis seperti pola MessageActivity di fahmi_PraktikumMobile
        val nama = intent.getStringExtra("SENDER") ?: "Calm"
        val pesan = intent.getStringExtra("PESAN") ?: ""
        val emoji = intent.getStringExtra("EMOJI") ?: "😌"
        val time = intent.getStringExtra("TIME") ?: "Today, 09:30 AM"
        val tags = intent.getStringExtra("TAGS") ?: "☕ Coffee  •  🎓 University  •  🎨 Studio"

        binding.txtNama.text = nama
        binding.txtPesan.text = pesan
        binding.txtDetailEmoji.text = emoji
        binding.txtDetailTime.text = time
        binding.txtDetailTags.text = tags

        // Navigasi Kembali dari Toolbar
        binding.toolbarDetail.setNavigationOnClickListener {
            finish()
        }

        // Navigasi Kembali dari tombol sesuai pola btnKembali
        binding.btnKembali.setOnClickListener {
            finish()
        }
    }
}
