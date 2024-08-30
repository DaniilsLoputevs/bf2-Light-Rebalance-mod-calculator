package io.github.bf2_mod_calculator.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.TooltipPlacement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

// TODO : impl in future
@Composable
@OptIn(ExperimentalFoundationApi::class)
fun warpWithTooltipTextAre(index: Int, item: String, content: @Composable () -> Unit) {
    TooltipArea(
        tooltip = {
            Surface(
                modifier = Modifier.shadow(4.dp),
//                color = MaterialTheme.colorScheme,
                color = Color(255, 255, 210),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = "value: 10 + ${item}${System.lineSeparator()}" +
                            "path: C:/111/222/333/444${System.lineSeparator()}" +
                            "line: 37${System.lineSeparator()}" +
                            "string: ObjectTemplate.damage 10",
                    modifier = Modifier.padding(10.dp)
                )
            }
        },
//        modifier = Modifier.padding(start = 40.dp),
        delayMillis = 400, // in milliseconds
        tooltipPlacement = TooltipPlacement.CursorPoint(
            alignment = Alignment.BottomEnd,
            offset = DpOffset((0).dp, (15).dp)
//            offset = if (index % 2 == 0) DpOffset((-16).dp, 2.dp) else DpOffset.Zero // tooltip offset
        ),
        content = content
    )
}