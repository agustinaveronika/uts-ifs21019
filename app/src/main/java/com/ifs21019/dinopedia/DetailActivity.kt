package com.ifs21019.dinopedia

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.ifs21019.dinopedia.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var dinosaurus: Dinosaurus? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dinosaurus = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(
                EXTRA_DINOSAURUS,
                Dinosaurus::class.java
            )
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DINOSAURUS)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (dinosaurus != null) {
            supportActionBar?.title = "Buah ${dinosaurus!!.name}"
            loadData(dinosaurus!!)
        } else {
            finish()
        }

        // Mendapatkan referensi ImageView untuk tombol kembali
        val backButton = findViewById<ImageView>(R.id.backDetail)

        // Menambahkan OnClickListener untuk tombol kembali
        backButton.setOnClickListener {
            finish() // Menutup aktivitas saat tombol kembali diklik
        }

        // Mendapatkan referensi ImageView untuk tombol share
        val shareButton = findViewById<ImageView>(R.id.imageView)

        // Menambahkan OnClickListener untuk tombol share
        shareButton.setOnClickListener {
            shareDinosaurusData() // Memanggil fungsi untuk berbagi data spot
        }
    }

    private fun loadData(dinosaurus: Dinosaurus) {
        binding.ivDetailIcon.setImageResource(dinosaurus.image)
        binding.tvDetailName.text = dinosaurus.name
        binding.tvDetailDescription.text = dinosaurus.description
        binding.tvDetailPeriode.text = dinosaurus.periode
        binding.textView14.text = dinosaurus.characteristic
        binding.textView16.text = dinosaurus.habitat
        binding.textView18.text = dinosaurus.act
        binding.textView20.text = dinosaurus.classification
    }

    private fun shareDinosaurusData() {
        // Contoh implementasi untuk berbagi data
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "Info dinosaurus: ${dinosaurus?.name} - ${dinosaurus?.description}")
        }
        startActivity(Intent.createChooser(shareIntent, "Bagikan info dinosaurus melalui:"))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun lihatDetailButtonClick(view: View) {
        // tidak ada perubahan karena Anda tidak perlu menangani klik tombol di sini
    }

    companion object {
        const val EXTRA_DINOSAURUS = "extra_dinosaurus"
    }
}