package com.eloinavarro.docscanner.ui.screens.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eloinavarro.docscanner.data.ScannedDocumentRepository
import com.eloinavarro.docscanner.domain.ScannedDocument
import java.util.UUID

class DetailViewModel constructor(
    private val scannedDocumentRepository: ScannedDocumentRepository,
    uuid: UUID
) :
    ViewModel() {
    val liveScannedDocument: LiveData<ScannedDocument> =
        scannedDocumentRepository.getLastLiveScannedDocument()
}