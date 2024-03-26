package com.eloinavarro.docscanner.ui.screens.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eloinavarro.docscanner.data.ScannedDocumentRepository
import com.eloinavarro.docscanner.domain.ScannedDocument
import java.util.UUID

class DetailViewModel constructor(
    private val scannedDocumentRepository: ScannedDocumentRepository,
    id: UUID
) :
    ViewModel() {
    private val currentDocument by lazy {
        val liveData = MutableLiveData<ScannedDocument>()
        scannedDocumentRepository.getScannedDocumentById(id) {
            liveData.value = it
        }
        return@lazy liveData
    }

    fun scannedDocument(): LiveData<ScannedDocument> = currentDocument
}