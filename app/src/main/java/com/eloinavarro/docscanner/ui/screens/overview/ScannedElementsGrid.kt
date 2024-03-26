package com.eloinavarro.docscanner.ui.screens.overview

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.eloinavarro.docscanner.R
import com.eloinavarro.docscanner.domain.ScannedDocument

@Composable
fun ScannedElementsGrid(
    onItemClick: (ScannedDocument) -> Unit,
    modifier: Modifier = Modifier,
    scannedDocuments: List<ScannedDocument> = emptyList(),
) {
    val currentDocuments by remember {
        mutableStateOf(scannedDocuments)
    }
    LazyVerticalGrid(
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_xsmall)),
        columns = GridCells.Adaptive(dimensionResource(id = R.dimen.cell_min_width)),
        modifier = modifier
    ) {
        items(currentDocuments.size) { index ->
            val uri = currentDocuments[index].thumbnailUri ?: currentDocuments[index].uri
            ScannedElement(
                uri = uri,
                onClick = {
                    onItemClick(currentDocuments[index])
                    Log.d("DEBUG", "ScannedElement $index clicked\n${currentDocuments[index]}")
                }
            )
        }
    }
}