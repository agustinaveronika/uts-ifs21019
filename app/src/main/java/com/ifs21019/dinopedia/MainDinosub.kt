package com.ifs21019.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21019.dinopedia.databinding.DinosubMainBinding

class MainDinosub : AppCompatActivity() {

    private lateinit var binding: DinosubMainBinding
    private val dataDino = ArrayList<Dino>()
    private var selectedDinosaurus: String? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dinosub_main)

        // Temukan tombol detail berdasarkan ID
        val detailButton: Button = findViewById(R.id.btnDetail)

        // Atur OnClickListener untuk tombol detail
        detailButton.setOnClickListener {
            // Buat Intent untuk memulai aktivitas MainDinosub
            val intent = Intent(this, MainDinosub::class.java)
            startActivity(intent)
        }

        // Menampilkan daftar dinosaurus
        showRecyclerList()
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_profile -> {
                val intent = Intent(this, UserProfile::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("Recycle")
    private fun getListDino(): ArrayList<Dino> {
        val dataNamadino = resources.getStringArray(R.array.dinosub_name)
        val dataGambardino = resources.obtainTypedArray(R.array.dinosub_icon)
        val dataDeskripsidino = resources.getStringArray(R.array.dinosub_description)
        val dataKarakteristikdino = resources.getStringArray(R.array.dinosub_characteristics)
        val dataKelompok = resources.getStringArray(R.array.dinosub_group)
        val dataHabitat = resources.getStringArray(R.array.dinosub_habitat)
        val dataMakanan = resources.getStringArray(R.array.dinosub_food)
        val dataPanjang = resources.getStringArray(R.array.dinosub_length)
        val dataTinggi = resources.getStringArray(R.array.dinosub_height)
        val dataBobot = resources.getStringArray(R.array.dinosub_weight)
        val dataKelemahan = resources.getStringArray(R.array.dinosub_weakness)

        val listDino = ArrayList<Dino>()
        for (i in dataNamadino.indices) {
            val dino = Dino(
                dataNamadino[i],
                dataGambardino.getResourceId(i, -1),
                dataDeskripsidino[i],
                dataKarakteristikdino[i],
                dataKelompok[i],
                dataHabitat[i],
                dataMakanan[i],
                dataPanjang[i],
                dataTinggi[i],
                dataBobot[i],
                dataKelemahan[i]
            )
            listDino.add(dino)
        }
        return listDino
    }


    @SuppressLint("Recycle")
    private fun getListDinoByFamily(family: String): ArrayList<Dino> {
        val allDino = getListDino()
        val filteredDino = ArrayList<Dino>()
        for (dino in allDino) {
            if (dino.kelompok == family) {
                filteredDino.add(dino)
            }
        }
        return filteredDino
    }

    private fun showRecyclerList() {
        if (resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvDino.layoutManager =
                GridLayoutManager(this, 2)
        } else {
            binding.rvDino.layoutManager =
                LinearLayoutManager(this)
        }

        val listDinoAdapter = DinoSubAdapter(dataDino)
        binding.rvDino.adapter = listDinoAdapter
        listDinoAdapter.setOnItemClickCallback(object :
            DinoSubAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Dino) {
                showSelectedDino(data)
            }
        })
    }

    private fun showSelectedDino(dino: Dino) {
        val intentWithData = Intent(this@MainDinosub,
           DetailDinosub::class.java)
        intentWithData.putExtra(DetailDinosub.EXTRA_DINO, dino)
        startActivity(intentWithData)
    }



    companion object {
        const val EXTRA_SELECTED_FAMILY = "EXTRA_SELECTED_FAMILY"
    }
}
