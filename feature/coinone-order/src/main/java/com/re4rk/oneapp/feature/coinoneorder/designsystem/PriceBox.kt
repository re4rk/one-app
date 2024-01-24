package com.re4rk.oneapp.feature.coinoneorder.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PriceBox(
    modifier: Modifier = Modifier,
    prefix: String = "",
    price: String = "",
    suffix: String = "",
    color: Color = Color(0xFFF8F8F9),
    shapes: RoundedCornerShape = RoundedCornerShape(8.dp),
) {
    Row(
        modifier = modifier
            .height(36.dp)
            .background(color, shapes)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,

    ) {
        Text(
            text = prefix,
            modifier = Modifier.wrapContentWidth(),
            color = Color(0xFF484D55),
            fontSize = 11.sp,
        )
        Text(
            text = price,
            modifier = Modifier
                .weight(1f)
                .clickable {
                },
            color = Color(0xFF18191C),
            textAlign = TextAlign.End,
            fontSize = 13.sp,
        )
        if (suffix.isNotEmpty()) {
            Spacer(modifier = Modifier.padding(1.dp))
            Text(
                text = suffix,
                modifier = Modifier.wrapContentWidth(),
                color = Color(0xFFBDBDBD),
                fontSize = 11.sp,
            )
        }
    }
}

@Composable
@Preview
fun PriceBoxPreview() {
    PriceBox(
        prefix = "가격",
        price = "1,000,000",
        suffix = "KRW",
    )
}
