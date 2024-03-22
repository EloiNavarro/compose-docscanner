package com.eloinavarro.docscanner.ui.screens.overview

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.eloinavarro.docscanner.R
import com.eloinavarro.docscanner.ui.common.ArrowBackIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OverviewTopbar(onBackClick: () -> Unit = {}) {
    TopAppBar(title = { Text(text = stringResource(R.string.txt_main_title)) },
        navigationIcon = {
            ArrowBackIcon(
                onUpClick = { onBackClick() }
            )
        }
    )
}