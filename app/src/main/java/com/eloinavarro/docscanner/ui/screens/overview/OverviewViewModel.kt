package com.eloinavarro.docscanner.ui.screens.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eloinavarro.docscanner.data.ScannedDocumentRepository
import com.eloinavarro.docscanner.domain.ScannedDocument

class OverviewViewModel constructor(private val scannedDocumentRepository: ScannedDocumentRepository) :
    ViewModel() {

    val liveScannedDocument: LiveData<ScannedDocument> =
        scannedDocumentRepository.getLastLiveScannedDocument()

    fun onNewDocumentScanned(document: ScannedDocument) {
        scannedDocumentRepository.addScannedDocument(document)
    }
}