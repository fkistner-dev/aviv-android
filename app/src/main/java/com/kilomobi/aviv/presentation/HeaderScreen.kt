package com.kilomobi.aviv.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kilomobi.aviv.R

@Composable
fun HeaderScreen(filterValue: Float, onFilterAction: (Float) -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        color = colorResource(id = R.color.AvivRed),
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "SeLoger",
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                ),
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            )
            IconButton(
                onClick = { },
                modifier = Modifier.padding(end = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "filter",
                    tint = Color.White
                )
            }
        }
    }
}
