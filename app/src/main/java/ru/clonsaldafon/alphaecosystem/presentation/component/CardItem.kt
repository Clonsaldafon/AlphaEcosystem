package ru.clonsaldafon.alphaecosystem.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.clonsaldafon.alphaecosystem.presentation.model.Card

@Composable
fun CardItem(
    card: Card?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(15.dp)
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(15.dp)
            )
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DataItem(
            title = "BIN",
            data = (if (card?.number == null) "" else card.number).toString()
        )

        DataItem(
            title = "Country",
            data = card?.country
        )

        DataItem(
            title = "Coordinates",
            data = card?.coordinates
        )

        DataItem(
            title = "Type",
            data = card?.type
        )

        DataItem(
            title = "Phone",
            data = card?.phone
        )

        DataItem(
            title = "Website",
            data = card?.website
        )

        DataItem(
            title = "City",
            data = card?.city
        )
    }
}