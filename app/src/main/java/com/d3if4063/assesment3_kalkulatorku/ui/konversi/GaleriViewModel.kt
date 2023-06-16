package com.d3if4063.assesment3_kalkulatorku.ui.konversi

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.d3if4063.assesment3_kalkulatorku.model.GaleriModel
import com.d3if4063.assesment3_kalkulatorku.network.DataApi
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.d3if4063.assesment3_kalkulatorku.MainActivity
import com.d3if4063.assesment3_kalkulatorku.network.ApiStatus
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit


class GaleriViewModel : ViewModel(){
    private val data = MutableLiveData<List<GaleriModel>>()
    private val status = MutableLiveData<ApiStatus>()

    init {
        retrieveData()
    }
    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            status.postValue(ApiStatus.LOADING)

            try {
                val result = DataApi.service.getData()
                data.postValue(result)
                status.postValue(ApiStatus.SUCCESS)

                Log.d("MainViewModel", "Success: $result")
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
                status.postValue(ApiStatus.FAILED)

            }
        }
    }

    fun getData():LiveData<List<GaleriModel>> = data

    fun getStatus(): LiveData<ApiStatus> = status

    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<com.d3if4063.assesment3_kalkulatorku.network.WorkManager>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(app).enqueueUniqueWork(
            MainActivity.CHANNEL_ID,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }

}