package com.eloinavarro.docscanner.domain

import android.net.Uri
import java.util.UUID

enum class ScannedDocumentType {
    PDF,
    IMAGE
}

data class ScannedDocument(
    val uri: Uri, val scannedDocumentType: ScannedDocumentType = ScannedDocumentType.IMAGE, val thumbnailUri: Uri? = null
) {
    val id: UUID = generateId()

    private fun generateId(): UUID {
        return UUID.nameUUIDFromBytes((uri.toString() + System.currentTimeMillis().toString()).toByteArray())
    }
}
