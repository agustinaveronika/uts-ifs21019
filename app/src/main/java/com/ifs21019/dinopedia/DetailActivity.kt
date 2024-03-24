package com.ifs21019.dinopedia

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.ifs21019.dinopedia.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var dinosaurus: Dinosaurus? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup ActionBar
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Detail Dinosaurus"
        }

        // Get Dinosaurus data from Intent
        dinosaurus = intent.getParcelableExtra(EXTRA_DINOSAURUS)

        // Set ActionBar title if Dinosaurus data is not null
        dinosaurus?.let {
            supportActionBar?.title = "Dino ${it.namefam}"
            loadData(it)
        } ?: finish()

        // Set OnClickListener for back button
        binding.backDetail.setOnClickListener {
            finish()
        }

        // Set OnClickListener for share button
        binding.imageView.setOnClickListener {
            shareDinosaurusData()
        }

        // Setup OnClickListener for btnDetail
        binding.btnDetail.setOnClickListener {
            openMainDinosub()
        }
    }

    // Function to open MainDinosub activity
    private fun openMainDinosub() {
        val intent = Intent(this, MainDinosub::class.java)
        startActivity(intent)
    }

    // Load Dinosaurus data into views
    private fun loadData(dinosaurus: Dinosaurus) {
        binding.ivDetailIcon.setImageResource(dinosaurus.imagefam)
        binding.tvDetailName.text = dinosaurus.namefam
        binding.tvDetailDescription.text = dinosaurus.descriptionfam
        binding.tvDetailPeriode.text = dinosaurus.periodefam
        binding.textView14.text = dinosaurus.characteristicfam
        binding.textView16.text = dinosaurus.habitatfam
        binding.textView18.text = dinosaurus.actfam
        binding.textView20.text = dinosaurus.classificationfam
    }

    // Function to share Dinosaurus data
    private fun shareDinosaurusData() {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(
                Intent.EXTRA_TEXT,
                "Info dinosaurus: ${dinosaurus?.namefam} - ${dinosaurus?.descriptionfam}"
            )
        }
        startActivity(Intent.createChooser(shareIntent, "Bagikan info dinosaurus melalui:"))
    }

    // Handle ActionBar back button click
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val EXTRA_DINOSAURUS = "EXTRA_DINOSAURUS"
    }
}
