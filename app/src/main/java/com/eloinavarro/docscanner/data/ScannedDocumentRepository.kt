package com.eloinavarro.docscanner.data

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import com.eloinavarro.docscanner.data.memory.OnMemoryDatasource
import com.eloinavarro.docscanner.domain.ScannedDocument
import java.util.UUID

object ScannedDocumentRepository {
    private val onMemoryDatasource = OnMemoryDatasource<ScannedDocument>()

    fun addScannedDocument(scannedDocument: ScannedDocument) {
        onMemoryDatasource.add(scannedDocument)
        Log.d("DEBUG", "ScannedDocumentRepository.addScannedDocument: $scannedDocument")
    }

    fun getLastLiveScannedDocument(): LiveData<ScannedDocument> {
        Log.d("DEBUG", "ScannedDocumentRepository.getLastLiveScannedDocument")
        return onMemoryDatasource.getLast()
    }

    fun getScannedDocumentById(id: UUID, onLoad: (ScannedDocument) -> Unit): ScannedDocument {
        return onMemoryDatasource.getAll().find { it.id == id } ?: ScannedDocument(Uri.EMPTY)
    }

    fun removeScannedDocument(scannedDocument: ScannedDocument) {
        onMemoryDatasource.remove(scannedDocument)
    }

    fun removeAllScannedDocuments() {
        onMemoryDatasource.getAll().forEach { onMemoryDatasource.remove(it) }
    }
}