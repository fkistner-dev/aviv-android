package com.kilomobi.aviv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.kilomobi.aviv.presentation.HeaderScreen
import com.kilomobi.aviv.presentation.list.PropertiesScreenState
import com.kilomobi.aviv.presentation.list.PropertiesScreen
import com.kilomobi.aviv.presentation.list.PropertiesViewModel
import com.kilomobi.aviv.ui.theme.AvivTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AvivTheme {
                val viewModel = PropertiesViewModel()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(id = R.color.AvivRed)
                ) {
                    Column {
                        HeaderScreen(
                            filterValue = 600f,
                            onFilterAction = { viewModel.updateFilterValue(it) }
                        )
                        PropertiesScreen(viewModel.state.value, {}, {}, {})
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PropertiesScreenPreview() {
    AvivTheme {
        PropertiesScreen(PropertiesScreenState(emptyList(), false, null, 0f), {}, {}, {})
    }
}