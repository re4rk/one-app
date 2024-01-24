package com.re4rk.oneapp.feature.coinoneorder

import androidx.compose.ui.graphics.Color

data class OrderScreenUiState(
    val tradeMode: TradeMode = TradeMode.BUY,
    val tradeWay: TradeWay = TradeWay.LIMIT,
)
enum class TradeMode(
    val text: String,
    val color: Color,
    val backgroundColor: Color,
) {
    BUY(
        text = "매수",
        color = Color(0xFFE0274F),
        backgroundColor = Color(0xFFFCE9ED),
    ),
    SELL(
        text = "매도",
        color = Color(0xFF1763B6),
        backgroundColor = Color(0xFFE8EFF8),
    ),
    ;

    companion object {
        fun getAll(): List<TradeMode> = listOf(BUY, SELL)
    }
}

enum class TradeWay(
    val text: String,
) {
    LIMIT(text = "지정가"),
    MARKET(text = "시장가"),
    RESERVATION(text = "예약가"),
    ;

    companion object {
        fun getAll(): List<TradeWay> = listOf(LIMIT, MARKET, RESERVATION)
    }
}
