package ru.clonsaldafon.alphaecosystem.presentation.view.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.clonsaldafon.alphaecosystem.R
import ru.clonsaldafon.alphaecosystem.presentation.component.CardItem
import ru.clonsaldafon.alphaecosystem.presentation.navigation.Routes

@Composable
fun CardDataScreen(
    modifier: Modifier = Modifier,
    navController: NavController? = null,
    viewModel: CardDataViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomAppBar {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_card),
                            contentDescription = null
                        )
                    }

                    IconButton(
                        onClick = {
                            navController?.navigate(Routes.History.route)
                        }
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_history),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color.White
                )
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        horizontal = 20.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 40.dp
                        ),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextField(
                        value = uiState.number,
                        onValueChange = { value ->
                            if (value.all { it.isDigit() })
                                viewModel.onEvent(CardDataEvent.OnNumberChanged(value))
                        },
                        placeholder = {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                text = "45717360",
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    textAlign = TextAlign.Center
                                )
                            )
                        },
                        supportingText = {
                            Text(
                                text = "Enter the first 6 to 8 digits of a card number (BIN/IIN)",
                                style = TextStyle(
                                    textAlign = TextAlign.Center
                                )
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        textStyle = TextStyle(
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        )
                    )

                    Button(
                        onClick = {
                            viewModel.onEvent(
                                CardDataEvent.OnSubmit { card ->

                                }
                            )
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                            contentDescription = null
                        )
                    }
                }

                CardItem(
                    card = uiState.card
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    locale = "ru"
)
@Composable
fun CardDataPreview() {
    CardDataScreen()
}