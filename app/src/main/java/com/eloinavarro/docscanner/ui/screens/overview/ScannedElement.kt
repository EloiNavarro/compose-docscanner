package com.eloinavarro.docscanner.ui.screens.overview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import coil.compose.AsyncImage
import com.eloinavarro.docscanner.R
import com.eloinavarro.docscanner.domain.ScannedDocument

@Composable
fun ScannedElement(
    scannedDocument: ScannedDocument,
    modifier: Modifier = Modifier,
    onClick: (ScannedDocument) -> Unit = {}
) {
    Card(
        modifier = modifier
            .clickable(onClick = { onClick(scannedDocument) })
            .height(dimensionResource(id = R.dimen.image_height))
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = scannedDocument.thumbnail,
            contentScale = ContentScale.Crop,
            contentDescription = "A scanned document",
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_small))
                .fillMaxSize()
        )
    }
}