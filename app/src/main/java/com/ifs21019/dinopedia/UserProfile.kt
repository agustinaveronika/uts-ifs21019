package com.ifs21019.dinopedia

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class UserProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        // Mendapatkan referensi TextView untuk email dan nomor telepon
        val emailTextView = findViewById<TextView>(R.id.email)
        val phoneTextView = findViewById<TextView>(R.id.noHP)

        // Menambahkan OnClickListener untuk email
        emailTextView.setOnClickListener {
            val email = emailTextView.text.toString()
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:$email")
            startActivity(intent)
        }

        // Menambahkan OnClickListener untuk nomor telepon
        phoneTextView.setOnClickListener {
            val phone = phoneTextView.text.toString()
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$phone")
            startActivity(intent)
        }
    }
}

