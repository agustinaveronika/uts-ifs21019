package com.ifs21019.dinopedia

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21019.dinopedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dataSpots = ArrayList<Dinosaurus>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvDinoFamily.setHasFixedSize(true) // Diganti menjadi true untuk performa yang lebih baik
        dataSpots.addAll(getListSpots())
        showRecyclerList()

        // Panggil fungsi setupProfileButton() untuk menambahkan fungsi onClickListener untuk ikon profil
        setupProfileButton()
    }

    // Fungsi untuk menambahkan fungsi onClickListener untuk tombol profil
    private fun setupProfileButton() {
        binding.ivProfile.setOnClickListener {
            // Ketika ikon profil diklik, Anda dapat membuka tampilan profil pengguna di sini
            // Misalnya:
            val intent = Intent(this, UserProfile::class.java)
            startActivity(intent)
        }
    }

    private fun getListSpots(): ArrayList<Dinosaurus> {
        val dataName = resources.getStringArray(R.array.dinos_namefam)
        val dataImage = resources.obtainTypedArray(R.array.dinos_imagefam)
        val dataDescription = resources.getStringArray(R.array.dinos_descfam)
        val dataPeriode = resources.getStringArray(R.array.dinos_periodefam)
        val dataCharacteristic = resources.getStringArray(R.array.dinos_characteristicfam)
        val dataHabitat = resources.getStringArray(R.array.dinos_habitatfam)
        val dataPerilaku = resources.getStringArray(R.array.dinos_actfam)
        val dataKlasifikasi = resources.getStringArray(R.array.dinos_classfam)
        val listSpot = ArrayList<Dinosaurus>()

        // Periksa panjang array sebelum iterasi
        val minSize = minOf(
            dataName.size,
            dataImage.length(),
            dataDescription.size,
            dataPeriode.size,
            dataCharacteristic.size,
            dataHabitat.size,
            dataPerilaku.size,
            dataKlasifikasi.size
        )

        for (i in 0 until minSize) {
            val dinosaurus = Dinosaurus(
                dataName[i],
                dataImage.getResourceId(i, -1),
                dataDescription[i],
                dataPeriode[i],
                dataCharacteristic[i],
                dataHabitat[i],
                dataPerilaku[i],
                dataKlasifikasi[i]
            )
            listSpot.add(dinosaurus)
        }
        // Release the TypedArray
        dataImage.recycle()
        return listSpot
    }


    private fun showRecyclerList() {
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvDinoFamily.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.rvDinoFamily.layoutManager = LinearLayoutManager(this)
        }

        val listSpotAdapter = ListDinoAdapter(dataSpots)
        binding.rvDinoFamily.adapter = listSpotAdapter

        listSpotAdapter.setOnItemClickCallback(object : ListDinoAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Dinosaurus) {
                showSelectedSpot(data)
            }
        })
    }

    private fun showSelectedSpot(dinosaurus: Dinosaurus) {
        val intentWithData = Intent(this@MainActivity, DetailActivity::class.java)
        intentWithData.putExtra(DetailActivity.EXTRA_DINOSAURUS, dinosaurus)
        startActivity(intentWithData)
    }
}

