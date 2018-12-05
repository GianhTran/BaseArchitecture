package com.bonseyjaden.basearchitecture.feature.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.Handler
import com.bonseyjaden.basearchitecture.data.model.Data
import com.bonseyjaden.basearchitecture.data.repository.DataRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {
    val key = MutableLiveData<String>()
    fun getData(): Data {
        Handler().postDelayed({
            key.postValue(dataRepository.getData().key)
        }, 5000)
        return dataRepository.getData()
    }
}