package com.eloinavarro.docscanner.ui.screens.overview

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.eloinavarro.docscanner.data.ScannedDocumentRepository
import com.eloinavarro.docscanner.domain.ScannedDocument
import com.eloinavarro.docscanner.domain.ScannedDocumentType
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult
import java.io.File
import java.io.FileOutputStream

class OverviewViewModel constructor(private val scannedDocumentRepository: ScannedDocumentRepository) :
    ViewModel() {

    val scannedDocuments: List<ScannedDocument> = scannedDocumentRepository.getAllScannedDocuments()

    fun setScanningResult(scanningResult: GmsDocumentScanningResult?) {
        scanningResult?.let {
            when(getScanningResultType(scanningResult)) {
                ScannedDocumentType.PDF -> handlePdfScanningResult(scanningResult)
                ScannedDocumentType.IMAGE -> handleImageScanningResult(scanningResult)
            }
        }
    }

    private fun handlePdfScanningResult(scanningResult: GmsDocumentScanningResult) {
        scanningResult.pdf?.uri?.let {
            // Since it's a PDF, we'll use the first page as the thumbnail
            val thumbnailUri = scanningResult.pages?.firstOrNull()?.imageUri
            scannedDocumentRepository.addScannedDocument(
                ScannedDocument(
                    it,
                    ScannedDocumentType.PDF,
                    thumbnailUri
                )
            )
        }
    }

    private fun handleImageScanningResult(scanningResult: GmsDocumentScanningResult) {
        scanningResult.pages?.forEach {
            scannedDocumentRepository.addScannedDocument(ScannedDocument(it.imageUri))
        }
    }

    private fun getScanningResultType(scanningResult: GmsDocumentScanningResult): ScannedDocumentType {
        return if (scanningResult.pdf != null) {
            ScannedDocumentType.PDF
        } else {
            ScannedDocumentType.IMAGE
        }
    }
}