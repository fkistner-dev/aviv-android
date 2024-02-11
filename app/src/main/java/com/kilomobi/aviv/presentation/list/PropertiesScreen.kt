package com.kilomobi.aviv.presentation.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.kilomobi.aviv.R
import com.kilomobi.aviv.domain.Property

@Composable
fun PropertiesScreen(
    state: PropertiesScreenState,
    loadProperties: () -> Unit,
    onPropertyClick: (Property) -> Unit,
    onFilterValueChanged: (Float) -> Unit
) {
    LazyColumn {
        if (state.properties.isNotEmpty()) {
            items(state.properties) {
                PropertyCard(property = it)
            }
        }
    }
}

@Composable
fun PropertyCard(property: Property) {
    Card(
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.AvivWhite)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
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
            Spacer(modifier = Modifier.height(8.dp))
            ListText(
                text = stringResource(R.string.list_city_text, property.city),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            if (property.propertyType?.isNotEmpty() == true) {
                ListText(text = stringResource(R.string.list_type_text, property.propertyType))
                Spacer(modifier = Modifier.height(4.dp))
            }
            ListText(text = stringResource(R.string.list_area_text, property.area))
        }
    }
}

@Composable
fun ListText(text: String, modifier: Modifier = Modifier, fontWeight: FontWeight = FontWeight.Normal) {
    Text(text = text, modifier = modifier, color = colorResource(id = R.color.AvivBlack))
}

@Preview(showBackground = true)
@Composable
fun PreviewPropertyCard() {
    val property = Property(
        bedrooms = 4,
        city = "Villers-sur-Mer",
        id = 1,
        area = 250.0,
        imageUrl = "https://v.seloger.com/s/crop/590x330/visuels/1/7/t/3/17t3fitclms3bzwv8qshbyzh9dw32e9l0p0udr80k.jpg",
        price = 1500000.0,
        professional = "GSL EXPLORE",
        propertyType = "Maison - Villa",
        offerType = 1,
        rooms = 8
    )

    PropertyCard(property = property)
}