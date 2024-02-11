package com.kilomobi.aviv.presentation.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.kilomobi.aviv.R
import com.kilomobi.aviv.domain.Property
import com.kilomobi.aviv.presentation.MockContent
import com.kilomobi.aviv.presentation.PropertyHighlightText
import com.kilomobi.aviv.presentation.formatPrice
import com.kilomobi.aviv.presentation.generateDescription

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
        PropertyHighlightText(property)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            property.price?.let {
                DetailsText(
                    text = stringResource(
                        id = R.string.details_price_text, it.formatPrice(
                            stringResource(id = R.string.details_price_symbol_text)
                        )
                    ),
                    color = colorResource(id = R.color.AvivBlack),
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        }

        if (property.propertyType?.isNotEmpty() == true) {
            DetailsText(
                text = stringResource(R.string.list_type_text, property.propertyType),
                color = colorResource(id = R.color.AvivBlack)
            )
            Spacer(modifier = Modifier.width(4.dp))
        }
        Card(
            colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.AvivRed)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
        ) {
            DetailsText(
                text = property.generateDescription()
            )
        }
    }
}

@Composable
fun DetailsText(text: String, color: Color = Color.White, style: TextStyle = MaterialTheme.typography.bodyLarge) {
    Text(
        text = text,
        style = style,
        color = color,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PropertyDetailScreenPreview() {
    val property = MockContent.getProperty()
    PropertyDetailScreen(property = property)
}
