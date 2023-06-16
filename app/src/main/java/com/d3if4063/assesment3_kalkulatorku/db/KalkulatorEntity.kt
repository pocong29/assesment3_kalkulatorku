package com.d3if4063.assesment3_kalkulatorku.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kalkulator")
data class KalkulatorEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var panjang: Float,
    var lebar: Float,
    var tinggi: Float,
    var jenis: String,
)
