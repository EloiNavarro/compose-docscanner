package com.eloinavarro.docscanner.data.memory

class OnMemoryDatasource<T> {
    private val data = mutableListOf<T>()

    fun add(item: T) {
        data.add(item)
    }

    fun remove(item: T) {
        data.remove(item)
    }

    fun getAll(): List<T> {
        return data
    }
}