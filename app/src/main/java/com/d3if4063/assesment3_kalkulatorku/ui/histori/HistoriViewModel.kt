package com.d3if4063.assesment3_kalkulatorku.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d3if4063.assesment3_kalkulatorku.db.KalkulatorDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoriViewModel(private val db: KalkulatorDao) : ViewModel() {
    val data = db.getLastKalkulator()

    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }
}