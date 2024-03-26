package com.eloinavarro.docscanner.ui.screens.detail

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Observer
import com.eloinavarro.docscanner.domain.ScannedDocument
import com.eloinavarro.docscanner.injection.Provider
import com.eloinavarro.docscanner.ui.common.ArrowBackIcon
import com.eloinavarro.docscanner.ui.common.Screen
import com.eloinavarro.docscanner.ui.screens.overview.ScannedElement
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(uuid: UUID, onUpClick: () -> Unit) {
    val viewModel = Provider.provideDetailViewModel(uuid)
    var uri by remember { mutableStateOf<Uri>(Uri.EMPTY) }
    val observer = Observer<ScannedDocument> { scannedDocument ->
        uri = scannedDocument.uri
        Log.d("DEBUG", "Scanned document: $scannedDocument")
    }
    viewModel.scannedDocument().observeForever(observer)

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
            ScannedElement(uri = uri,
                Modifier
                    .padding(padding)
                    .fillMaxSize())
        }
    }
}