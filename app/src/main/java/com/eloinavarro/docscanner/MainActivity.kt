package com.eloinavarro.docscanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.eloinavarro.docscanner.ui.navigation.Navigation

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Navigation()
            /*
            AppTheme {
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
                                        UploadType.CAMERA -> { /* TODO */
                                        }

                                        UploadType.GALLERY -> { /* TODO */
                                        }

                                        UploadType.DOCUMENT_SCANNER -> onDocumentScanning(result)
                                        else -> { /* Nothing to do? */
                                        }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Topbar(onBackClick: () -> Unit = {}) {
    TopAppBar(title = { Text(text = stringResource(R.string.txt_main_title)) },
        navigationIcon = {
            ArrowBackIcon(
                onUpClick = { onBackClick() }
            )
        }
    )
}

@Composable
fun Bottombar(onUploadClick: () -> Unit = {}) {
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
    AppTheme {
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
        BoxedTextField(
            label = "Update title",
            value = "",
            onValueChange = {}
        )
        BoxedTextField(
            label = "Add description",
            value = "",
            onValueChange = {}
        )
        Column {
            TextDivider("Assets to upload")
            ImagesToUploadRow(imageUris)
             */
        }
    }
}
