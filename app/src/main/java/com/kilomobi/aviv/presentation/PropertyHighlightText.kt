package com.kilomobi.aviv.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kilomobi.aviv.R
import com.kilomobi.aviv.domain.Property

@Composable
fun PropertyHighlightText(property: Property) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        ListText(
            text = stringResource(R.string.list_city_text, property.city),
        )
        Spacer(modifier = Modifier.width(4.dp))
        ListText(
            text = stringResource(
                R.string.list_area_text, property.area.formatArea(
                    stringResource(id = R.string.details_area_unit_text)
                )
            )
        )
    }
}

@Composable
fun ListText(text: String, modifier: Modifier = Modifier, softWrap: Boolean = false) {
    Text(
        text = text,
        modifier = modifier,
        color = colorResource(id = R.color.AvivBlack),
        softWrap = softWrap
    )
}