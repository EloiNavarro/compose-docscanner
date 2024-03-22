package com.eloinavarro.docscanner.ui.screens.overview

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.DocumentScanner
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable

@Composable
fun OverviewBottombar(onUploadClick: () -> Unit = {}) {
    BottomAppBar(
        actions = {
            IconButton(onClick = { onUploadClick() }) {
                Icon(Icons.Outlined.Image, contentDescription = "Select from device")
            }
            IconButton(onClick = { onUploadClick() }) {
                Icon(Icons.Outlined.DocumentScanner, contentDescription = "Scan document")
            }
            IconButton(onClick = { onUploadClick() }) {
                Icon(Icons.Outlined.CameraAlt, contentDescription = "Select from device")
            }
        }
    )
}