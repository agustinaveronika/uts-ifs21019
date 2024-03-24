package com.ifs21019.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dino(
    var name: String,
    var image: Int,
    var description: String,
    var characteristic: String,
    var habitat: String,
    var kelompok: String,
    var makanan: String,
    var panjang: String,
    var tinggi: String,
    var bobot: String,
    var kelemahan: String

) : Parcelable
