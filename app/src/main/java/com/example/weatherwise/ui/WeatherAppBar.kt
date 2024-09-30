package com.example.weatherwise.ui

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherwise.WeatherViewModel
import com.example.weatherwise.ui.theme.WeatherWiseTheme
import com.example.weatherwise.ui.theme.russoOneRegular
import com.example.weatherwise.ui.theme.topAppBarBackgroundColor
import com.example.weatherwise.ui.theme.topAppBarContentColor
import com.example.weatherwise.utils.SearchAppBarState
import com.example.weatherwise.utils.TrailingIconState

@Composable
fun DisplayAppBar(
    weatherViewModel: WeatherViewModel,
    searchAppBarState: SearchAppBarState,
    searchTextState: String,
) {
    when (searchAppBarState) {
        SearchAppBarState.CLOSED -> {
            DefaultAppBar(
                onSearchClicked = {
                    weatherViewModel.searchAppBarState.value = SearchAppBarState.OPENED
                }
            )
        }

        SearchAppBarState.OPENED -> {
            SearchAppBar(
                text = searchTextState,
                onTextChanged = { value ->
                    weatherViewModel.searchTextState.value = value
                },
                onSearchClicked = {
                    weatherViewModel.fetchCityWeatherData()
                },
                onCloseClicked = {
                    weatherViewModel.searchAppBarState.value = SearchAppBarState.CLOSED
                }
            )
        }
    }
}

@Composable
fun SearchAppBar(
    text: String,
    onTextChanged: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onCloseClicked: () -> Unit,
) {

    var trailingIconState by remember {
        mutableStateOf(TrailingIconState.READY_TO_DELETE)
    }

    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        color = MaterialTheme.colorScheme.topAppBarBackgroundColor
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { changedValue ->
                onTextChanged(changedValue)
            },
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.topAppBarContentColor,
                fontFamily = russoOneRegular
            ),
            singleLine = true,
            placeholder = {
                Text(
                    text = "Search City",
                    color = MaterialTheme.colorScheme.topAppBarContentColor,
                    modifier = Modifier.alpha(0.5f)
                )
            },
            leadingIcon = {
                IconButton(
                    onClick = {

                    },
                    modifier = Modifier.alpha(0.5f)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search Icon",
                        tint = MaterialTheme.colorScheme.topAppBarContentColor
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        when (trailingIconState) {
                            TrailingIconState.READY_TO_DELETE -> {
                                onTextChanged("")
                                trailingIconState = TrailingIconState.READY_TO_CLOSE
                            }

                            TrailingIconState.READY_TO_CLOSE -> {
                                if (text.isNotBlank()) {
                                    onTextChanged("")
                                } else {
                                    onCloseClicked()
                                    trailingIconState = TrailingIconState.READY_TO_DELETE
                                }
                            }
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Close Icon",
                        tint = MaterialTheme.colorScheme.topAppBarContentColor
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    if (text.isNotBlank()) {
                        onSearchClicked(text)
                    } else {
                        Toast.makeText(context, "Not a valid text", Toast.LENGTH_SHORT).show()
                    }
                }
            ),
            colors = TextFieldDefaults.colors(
                cursorColor = MaterialTheme.colorScheme.topAppBarContentColor,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultAppBar(
    onSearchClicked: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = "City Search",
                color = MaterialTheme.colorScheme.topAppBarContentColor,
                fontFamily = russoOneRegular,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.topAppBarBackgroundColor
        ),
        actions = {
            AppBarAction(
                onSearchClicked
            )
        }
    )
}

@Composable
fun AppBarAction(
    onSearchClicked: () -> Unit,
) {
    SearchAction(onSearchClicked = onSearchClicked)
}

@Composable
fun SearchAction(
    onSearchClicked: () -> Unit,
) {
    IconButton(
        onClick = {
            onSearchClicked()
        }
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "Search button",
            tint = MaterialTheme.colorScheme.topAppBarContentColor
        )
    }
}

@Preview
@Composable
private fun DefaultAppBarPreview() {
    WeatherWiseTheme {
        DefaultAppBar(
            onSearchClicked = { }
        )
    }
}

@Preview
@Composable
private fun SearchAppBarPreview() {
    WeatherWiseTheme {
        SearchAppBar(
            text = "",
            onTextChanged = {},
            onSearchClicked = {},
            onCloseClicked = {}
        )
    }
}
