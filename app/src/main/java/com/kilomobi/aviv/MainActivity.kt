package com.kilomobi.aviv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kilomobi.aviv.domain.Property
import com.kilomobi.aviv.presentation.HeaderScreen
import com.kilomobi.aviv.presentation.details.PropertyDetailScreen
import com.kilomobi.aviv.presentation.list.PropertiesScreenState
import com.kilomobi.aviv.presentation.list.PropertiesScreen
import com.kilomobi.aviv.presentation.list.PropertiesViewModel
import com.kilomobi.aviv.ui.theme.AvivTheme

class MainActivity : ComponentActivity() {

    companion object {
        const val DESTINATION_PROPERTIES_SCREEN = "destination_properties_screen"
        const val DESTINATION_PROPERTY_DETAIL_SCREEN = "destination_property_detail_screen"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AvivTheme {
                val viewModel = PropertiesViewModel()
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = DESTINATION_PROPERTIES_SCREEN
                ) {
                    composable(DESTINATION_PROPERTIES_SCREEN) {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = colorResource(id = R.color.AvivRed)
                        ) {
                            Column {
                                HeaderScreen(
                                    filterValue = 600f,
                                    onFilterAction = { viewModel.updateFilterValue(it) }
                                )
                                PropertiesScreen(viewModel.state.value, {}, {
                                    ActivityHelper.selectedProperty = it
                                    navController.navigate(DESTINATION_PROPERTY_DETAIL_SCREEN)
                                }, {})
                            }
                        }
                    }
                    composable(
                        route = DESTINATION_PROPERTY_DETAIL_SCREEN,
                        enterTransition = { slideInHorizontally(animationSpec = tween(500)) },
                        exitTransition = { slideOutHorizontally(animationSpec = tween(500)) },
                    ) { _ ->
                        PropertyDetailScreen(ActivityHelper.selectedProperty!!)
                    }
                }
            }
        }
    }
}

object ActivityHelper {
    var selectedProperty: Property? = null
}

@Preview(showBackground = true)
@Composable
fun PropertiesScreenPreview() {
    AvivTheme {
        PropertiesScreen(PropertiesScreenState(emptyList(), false, null, 0f), {}, {}, {})
    }
}