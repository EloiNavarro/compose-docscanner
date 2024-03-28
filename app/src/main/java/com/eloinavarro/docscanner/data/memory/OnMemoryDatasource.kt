package com.eloinavarro.docscanner.data.memory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class OnMemoryDatasource<T> {
    private val data = mutableListOf<T>()
    private val lastData = MutableLiveData<T>()

    fun add(item: T) {
        data.add(item)
        lastData.value = item!!
    }

    fun remove(item: T) {
        data.remove(item)
    }

    fun getAll(): List<T> {
        return data
    }

    fun getLast(): LiveData<T> {
        return lastData
    }
}