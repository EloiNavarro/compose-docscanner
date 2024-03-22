package com.eloinavarro.docscanner.ui.screens.detail

import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.eloinavarro.docscanner.ui.common.ArrowBackIcon
import com.eloinavarro.docscanner.ui.common.Screen
import com.eloinavarro.docscanner.ui.screens.overview.ScannedElement

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(uri: Uri, onUpClick: () -> Unit) {
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
            ScannedElement(uri = uri, Modifier.padding(padding))
        }
    }
}