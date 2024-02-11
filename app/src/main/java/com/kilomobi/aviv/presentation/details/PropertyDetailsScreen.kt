package com.kilomobi.aviv.presentation.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.kilomobi.aviv.R
import com.kilomobi.aviv.domain.Property
import com.kilomobi.aviv.presentation.MockContent
import com.kilomobi.aviv.presentation.formatArea
import com.kilomobi.aviv.presentation.formatPrice

@Composable
fun PropertyDetailScreen(property: Property) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        property.imageUrl?.let { imageUrl ->
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
        }
        DetailsText(
            text = stringResource(id = R.string.details_city_text, property.city)
        )
        property.price?.let {
            DetailsText(
                text = stringResource(
                    id = R.string.details_price_text, it.formatPrice(
                        stringResource(id = R.string.details_price_symbol_text)
                    )
                )
            )
        }
        DetailsText(
            text = stringResource(
                id = R.string.details_area_text, property.area.formatArea(
                    stringResource(id = R.string.details_area_unit_text)
                )
            )
        )
        property.bedrooms?.let {
            DetailsText(
                text = stringResource(id = R.string.details_bedrooms_text, it)
            )
        }
        property.rooms?.let {
            DetailsText(
                text = stringResource(id = R.string.details_rooms_text, it)
            )
        }
        property.professional?.let {
            DetailsText(
                text = stringResource(id = R.string.details_professional_text, it)
            )
        }
        property.propertyType?.let {
            DetailsText(
                text = stringResource(id = R.string.details_property_type_text, it)
            )
        }
        DetailsText(
            text = stringResource(id = R.string.details_offer_type_text, property.offerType)
        )
    }
}

@Composable
fun DetailsText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        color = Color.Black,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PropertyDetailScreenPreview() {
    val property = MockContent.getProperty()
    PropertyDetailScreen(property = property)
}
