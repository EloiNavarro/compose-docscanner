package com.eloinavarro.docscanner.domain

import android.net.Uri
import java.util.UUID

data class ScannedDocument(val uri: Uri, val pages: List<Uri> = emptyList(), val timestamp: Long = 0) {
    val id: UUID = generateId()
    val thumbnail: Uri = getThumbnailUri()

    private fun generateId(): UUID {
        return UUID.nameUUIDFromBytes((uri.toString() + timestamp.toString()).toByteArray())
    }

    private fun getThumbnailUri(): Uri {
        return if(pages.isNotEmpty()) {
            pages[0]
        } else {
            uri
        }
    }
}
