package com.eloinavarro.docscanner.ui.screens.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.eloinavarro.docscanner.injection.Provider
import com.eloinavarro.docscanner.ui.common.ArrowBackIcon
import com.eloinavarro.docscanner.ui.common.Screen
import com.eloinavarro.docscanner.ui.screens.overview.ScannedElementPages
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(uuid: UUID, onUpClick: () -> Unit) {
    val viewModel = Provider.provideDetailViewModel(uuid)
    val scannedDocument by remember {
        mutableStateOf(viewModel.liveScannedDocument.value!!)
    }
    Screen {
        Scaffold(
            topBar = {
                TopAppBar(title = { },
                    navigationIcon = {
                        ArrowBackIcon(onUpClick)
                    }
                )
            }
        ) { padding ->
            ScannedElementPages(
                scannedDocument = scannedDocument,
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            )
        }
    }
}