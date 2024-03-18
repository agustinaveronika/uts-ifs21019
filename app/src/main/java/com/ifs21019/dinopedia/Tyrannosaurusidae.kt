package com.ifs21019.dinopedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_tyrannosaurusidae.*

class Tyrannosaurusidae : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tyrannosaurusidae)

        // Mengatur onClickListener untuk tombol "Lihat Detail"
        btnDetail.setOnClickListener {
            // Membuat Intent untuk berpindah ke DetailActivity
            val intent = Intent(this, DetailActivity::class.java)
            // Mengirim data detail
            intent.putExtra("detailName", "Tyrannosaurusidae")
            intent.putExtra("detailDescription", "Ini adalah deskripsi Tyrannosaurusidae.")
            // Menjalankan Intent
            startActivity(intent)
        }
    }
}

