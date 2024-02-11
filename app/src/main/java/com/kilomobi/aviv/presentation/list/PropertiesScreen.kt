package com.kilomobi.aviv.presentation.list

import androidx.annotation.ColorRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.kilomobi.aviv.R
import com.kilomobi.aviv.domain.Property
import com.kilomobi.aviv.presentation.MockContent
import com.kilomobi.aviv.presentation.PropertyHighlightText

@Composable
fun PropertiesScreen(
    state: PropertiesScreenState,
    loadProperties: () -> Unit,
    onPropertyClick: (Property) -> Unit) {

    if (state.isLoading) {
        CircularProgressIndicator(
            modifier = Modifier.fillMaxSize(),
            color = colorResource(id = R.color.AvivBlack),
            trackColor = colorResource(id = R.color.AvivRed),
        )
    }
    if (state.error != null) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = state.error,
                modifier = Modifier.align(Alignment.Center),
                color = colorResource(id = R.color.AvivWhite)
            )
            Button(
                onClick = { loadProperties() },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.AvivWhite)),
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                Text(text = stringResource(id = R.string.common_retry), color = colorResource(id = R.color.AvivBlack))
            }
        }
    }
    if (state.properties.isNotEmpty()) {
        LazyColumn {
            items(state.properties) {
                PropertyCard(property = it, loadProperties, onPropertyClick)
            }
        }
    }
}

@Composable
fun PropertyCard(
    property: Property,
    loadProperties: () -> Unit,
    onPropertyClick: (Property) -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.AvivWhite)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                onPropertyClick(property)
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            property.imageUrl?.let { imageUrl ->
                Image(
                    painter = rememberAsyncImagePainter(imageUrl),
                    contentDescription = "image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                )
            }
            PropertyHighlightText(property)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPropertyCard() {
    val property = MockContent.getProperty()
    PropertyCard(property = property, {}, {})
}