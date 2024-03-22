package com.eloinavarro.docscanner.ui.screens.overview

import android.net.Uri
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

@Composable
fun ScannedElementsGrid(
    onItemClick: (Uri) -> Unit,
    modifier: Modifier = Modifier,
    uris: List<Uri> = emptyList(),
) {
    val currentUris by remember {
        mutableStateOf(uris)
    }
    LazyVerticalGrid(
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_xsmall)),
        columns = GridCells.Adaptive(dimensionResource(id = R.dimen.cell_min_width)),
        modifier = modifier
    ) {
        items(currentUris.size) { index ->
            ScannedElement(uri = currentUris[index], onClick = { onItemClick(currentUris[index]) })
        }
    }
}