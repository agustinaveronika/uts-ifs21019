package com.ifs21019.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dinosaurus(
    var name: String,
    var image: Int,
    var description: String,
    var periode: String,
    var characteristic: String,
    var habitat: String,
    var act: String,
    var classification: String

) : Parcelable
