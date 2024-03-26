package com.eloinavarro.docscanner.data

import android.net.Uri
import android.util.Log
import com.eloinavarro.docscanner.data.memory.OnMemoryDatasource
import com.eloinavarro.docscanner.domain.ScannedDocument
import java.util.UUID

object ScannedDocumentRepository {
    private val onMemoryDatasource = OnMemoryDatasource<ScannedDocument>()

    fun addScannedDocument(scannedDocument: ScannedDocument) {
        onMemoryDatasource.add(scannedDocument)
        Log.d("DEBUG", "ScannedDocumentRepository.addScannedDocument: $scannedDocument")
    }

    fun removeScannedDocument(scannedDocument: ScannedDocument) {
        onMemoryDatasource.remove(scannedDocument)
    }

    fun getAllScannedDocuments(): List<ScannedDocument> {
        Log.d("DEBUG", "ScannedDocumentRepository.getAllScannedDocuments")
        return onMemoryDatasource.getAll()
    }

    fun getScannedDocumentById(id: UUID, onLoad: (ScannedDocument) -> Unit): ScannedDocument {
        Log.d("DEBUG", "ScannedDocumentRepository.getScannedDocumentById: $id")
        return onMemoryDatasource.getAll().find { it.id == id } ?: ScannedDocument(Uri.EMPTY)
    }

    fun deleteAllScannedDocuments() {
        Log.d("DEBUG", "ScannedDocumentRepository.deleteAllScannedDocuments")
        onMemoryDatasource.getAll().forEach { onMemoryDatasource.remove(it) }
    }
}