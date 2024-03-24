package com.ifs21019.dinopedia

import android.os.Bundle
import android.view.MenuItem
import android.view.Menu
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.ifs21019.dinopedia.databinding.DinosubDetailBinding


class DetailDinosub : AppCompatActivity() {

    private lateinit var binding: DinosubDetailBinding
    private var dino: Dino? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DinosubDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dino = intent.getParcelableExtra(EXTRA_DINO)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (dino != null) {
            supportActionBar?.title = "${dino!!.name}"
            loadData(dino!!)
        } else {
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.action_share -> {
                shareContent()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun shareContent() {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        val shareMessage = """
            Nama: ${dino?.name}
            Deskripsi: ${dino?.description}
            Karakteristik: ${dino?.characteristic}
            Kelompok: ${dino?.kelompok}
            Habitat: ${dino?.habitat}
            Makanan: ${dino?.makanan}
            Panjang: ${dino?.panjang}
            Tinggi: ${dino?.tinggi}
            Bobot : ${dino?.bobot}
         Kelemahan: ${dino?.kelemahan}
        """.trimIndent()
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
        startActivity(Intent.createChooser(shareIntent, "Share Dinosaurus"))
    }

    private fun loadData(dino: Dino) {
        binding.ivDetailIcon.setImageResource(dino.image)
        binding.tvDetailName.text = dino.name
        binding.tvDetailDescription.text = dino.description
        binding.tvDetailKarakter.text = dino.characteristic
        binding.tvDetailKelompok.text = dino.kelompok
        binding.tvDetailHabitat.text = dino.habitat
        binding.tvDetailMakanan.text = dino.makanan
        binding.tvDetailPanjang.text = dino.panjang
        binding.tvDetailTinggi.text = dino.tinggi
        binding.tvDetailBobot.text = dino.bobot
        binding.tvDetailKelemahan.text = dino.kelemahan
    }

    companion object {
        const val EXTRA_DINO = "EXTRA_DINO"
    }
}