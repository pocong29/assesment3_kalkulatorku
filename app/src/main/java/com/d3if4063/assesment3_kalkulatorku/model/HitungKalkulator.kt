package com.d3if4063.assesment3_kalkulatorku.model

import com.d3if4063.assesment3_kalkulatorku.db.KalkulatorEntity

fun KalkulatorEntity.hitungKalkulator(): HasilLuas {
    val kalkulator = panjang * lebar * tinggi

    return HasilLuas(kalkulator)
}