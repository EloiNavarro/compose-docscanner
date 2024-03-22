package com.eloinavarro.docscanner.ui.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.eloinavarro.docscanner.R

@Composable
@Preview(showBackground = true, widthDp = 320, heightDp = 400)
fun FancyTextPreview() {
    FancyText("Hello, World!")
}

@Composable
fun FancyText(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
        style = MaterialTheme.typography.headlineLarge.copy(
            color = colorScheme.primary
        )
    )
}
