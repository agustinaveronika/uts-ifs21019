package com.ifs21019.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dinosaurus(
    var namefam: String,
    var imagefam: Int, // Perhatikan tipe data yang digunakan untuk merepresentasikan gambar
    var descriptionfam: String,
    var periodefam: String,
    var characteristicfam: String,
    var habitatfam: String,
    var actfam: String,
    var classificationfam: String
) : Parcelable
