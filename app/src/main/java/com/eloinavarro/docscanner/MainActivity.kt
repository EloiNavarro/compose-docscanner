package com.eloinavarro.docscanner

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.DocumentScanner
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.eloinavarro.docscanner.ui.theme.DocScannerPOVTheme
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.RESULT_FORMAT_JPEG
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.RESULT_FORMAT_PDF
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult

class MainActivity : ComponentActivity() {
    private val urisToUpload = emptyList<Uri>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var uploadType = mutableStateOf(UploadType.NONE)


        val options = GmsDocumentScannerOptions.Builder()
            .setScannerMode(GmsDocumentScannerOptions.SCANNER_MODE_FULL)
            .setGalleryImportAllowed(true)
            .setResultFormats(RESULT_FORMAT_JPEG, RESULT_FORMAT_PDF)
            .build()
        val scanner = GmsDocumentScanning.getClient(options)

        setContent {
            DocScannerPOVTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var imagesUris by remember {
                        mutableStateOf<List<Uri>>(emptyList())
                    }
                    val scannerLauncher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.StartIntentSenderForResult(),
                        onResult = { activityResult ->
                            if (activityResult.resultCode == RESULT_OK) {
                                val result =
                                    GmsDocumentScanningResult.fromActivityResultIntent(
                                        activityResult.data
                                    )
                                imagesUris = result?.pages?.map { it.imageUri } ?: emptyList()
                                result?.let {
                                    when (uploadType.value) {
                                        UploadType.CAMERA -> { /* TODO */ }
                                        UploadType.GALLERY -> { /* TODO */ }
                                        UploadType.DOCUMENT_SCANNER -> onDocumentScanning(result)
                                        else -> { /* Nothing to do? */ }
                                    }
                                }
                            }
                        }
                    )
                    UploadScreen(
                        imageUris = imagesUris,
                        onUploadClick = { currentUploadType ->
                            uploadType.value = currentUploadType
                            scanner.getStartScanIntent(this@MainActivity)
                                .addOnSuccessListener {
                                    scannerLauncher.launch(
                                        IntentSenderRequest.Builder(it).build()
                                    )
                                }
                                .addOnFailureListener {
                                    Toast.makeText(
                                        this@MainActivity,
                                        "Failed to start scanner",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                        }
                    )
                }
            }
        }
    }

    private fun onDocumentScanning(result: GmsDocumentScanningResult): List<Uri> {
        result.pdf?.let { pdf ->
            // We add the document to the list
            urisToUpload.plus(pdf.uri)
        }
        // But regardless of num of pages, we only show the first one
        return result.pages?.take(1)?.map { it.imageUri } ?: emptyList()
    }
}


@Composable
fun ArrowBackIcon(onUpClick: () -> Unit) {
    IconButton(onClick = onUpClick) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = null
        )
    }
}

@Composable
fun OutlinedTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(label)
        }
    )
}

@Composable
fun TextDivider(value: String) {
    Text(
        text = value.uppercase(),
        fontSize = 11.sp,
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
        textAlign = TextAlign.Left
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Topbar(onBackClick: () -> Unit = {}) {
    TopAppBar(title = { Text(text = "Upload assets") },
        navigationIcon = {
            ArrowBackIcon(
                onUpClick = { onBackClick() }
            )
        }
    )
}

@Composable
fun Bottombar(onUploadClick: (UploadType) -> Unit = {}) {
    BottomAppBar(
        actions = {
            IconButton(onClick = { onUploadClick(UploadType.GALLERY) }) {
                Icon(Icons.Outlined.Image, contentDescription = "Select from device")
            }
            IconButton(onClick = { onUploadClick(UploadType.DOCUMENT_SCANNER) }) {
                Icon(Icons.Outlined.DocumentScanner, contentDescription = "Scan document")
            }
            IconButton(onClick = { onUploadClick(UploadType.CAMERA) }) {
                Icon(Icons.Outlined.CameraAlt, contentDescription = "Select from device")
            }
        }
    )
}

@Composable
fun UploadScreen(imageUris: List<Uri>, onUploadClick: (UploadType) -> Unit = {}) {
    Scaffold(
        topBar = { Topbar() },
        bottomBar = { Bottombar(onUploadClick) }
    ) { padding ->
        UploadForm(
            imageUris,
            Modifier.padding(padding)
        )
    }
}

@Composable
fun ImagesToUploadRow(imageUris: List<Uri>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.Start
        )
    ) {
        items(imageUris.size) { index ->
            // Display the image
            AsyncImage(
                model = imageUris[index],
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(64.dp)
                    .height(64.dp)
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 700)
@Composable
fun Test() {
    DocScannerPOVTheme {
        UploadScreen(imageUris = emptyList())
    }
}

@Composable
fun UploadForm(
    imageUris: List<Uri>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .fillMaxHeight()
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.Top
        )
    ) {
        TextDivider("Administrative options")
        OutlinedTextField(
            label = "Update title",
            value = "",
            onValueChange = {}
        )
        OutlinedTextField(
            label = "Add description",
            value = "",
            onValueChange = {}
        )
        Column {
            TextDivider("Assets to upload")
            ImagesToUploadRow(imageUris)
        }
    }
}