package ru.clonsaldafon.alphaecosystem.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun DataItem(
    title: String?,
    data: String?
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title ?: "",
            style = TextStyle(
                color = Color.LightGray,
                fontSize = 14.sp,
                textAlign = TextAlign.Start
            )
        )

        Text(
            text = data ?: "",
            style = TextStyle(
                fontSize = 20.sp,
                textAlign = TextAlign.End
            )
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun DataItemPreview() {
    DataItem(
        title = "BRAND",
        data = "Visa"
    )
}