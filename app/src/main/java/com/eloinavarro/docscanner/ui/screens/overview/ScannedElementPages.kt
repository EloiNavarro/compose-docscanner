package com.eloinavarro.docscanner.ui.screens.overview

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import coil.compose.AsyncImage
import com.eloinavarro.docscanner.R
import com.eloinavarro.docscanner.domain.ScannedDocument

@Composable
fun ScannedElementPages(
    modifier: Modifier = Modifier,
    scannedDocument: ScannedDocument,
) {
    LazyHorizontalGrid(
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_medium)),
        rows = GridCells.Fixed(1),
        modifier = modifier
    ) {
        items(scannedDocument.pages.size) { index ->
            AsyncImage(
                model = scannedDocument.pages[index],
                contentScale = ContentScale.Crop,
                contentDescription = "A scanned page",
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .fillMaxSize()
            )
        }
    }
}