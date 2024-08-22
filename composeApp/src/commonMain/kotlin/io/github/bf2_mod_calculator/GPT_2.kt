package io.github.bf2_mod_calculator

//import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import io.github.bf2_mod_calculator.gui.warpWithTooltipTextAre
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SelectableTable() {
    val leftColumnItems = listOf("Category 1", "Category 2", "Category 3")
    val rightColumnItems = mapOf(
        "Category 1" to listOf("Item 1.1", "Item 1.2", "Item 1.3"),
        "Category 2" to listOf("Item 2.1", "Item 2.2", "Item 2.3"),
        "Category 3" to listOf("Item 3.1", "Item 3.2", "Item 3.3")
    )
    var selectedLeftItem by remember { mutableStateOf<String?>(null) }
    var selectedRightItem by remember { mutableStateOf<String?>(null) }
    Box {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            // Левая колонка
            Column(modifier = Modifier.weight(1f).padding(end = 8.dp)) {
                leftColumnItems.forEach { item ->
                    Text(
                        text = item,
                        color = if (item == selectedLeftItem) Color.Blue else Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedLeftItem = item
                                selectedRightItem = null // сброс выбранного элемента в правой колонке
                            }
                            .border(1.dp, Color.Blue)
                            .padding(8.dp)
                    )
                }
            }

            // Правая колонка
            Column(modifier = Modifier.weight(1f)) {
                selectedLeftItem?.let { selectedItem ->
                    rightColumnItems[selectedItem]?.forEachIndexed { index, item ->
                        warpWithTooltipTextAre(index, item) {
                            Text(
                                text = item,
                                color = if (item == selectedRightItem) Color.Blue else Color.Black,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        selectedRightItem = item
                                    }
                                    .padding(8.dp)
                            )
                        }


                    }
                }
            }
        }
    }
}

@Preview()
@Composable
fun SelectableTablePreview() {
    SelectableTable()
}

//@Composable
//private inline fun ColumnScope.makeColumnCell() {
//    Text(
//        text = item,
//        color = if (item == selectedRightItem) Color.Blue else Color.Black,
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable {
//                selectedRightItem = item
//            }
//            .padding(8.dp)
//    )
//}

//@Composable
//@OptIn(ExperimentalFoundationApi::class)
//private fun warpWithTooltipTextAre(index: Int, item: String, content: @Composable () -> Unit) {
//    TooltipArea(
//        tooltip = {
//            Surface(
//                modifier = Modifier.shadow(4.dp),
//                color = Color(255, 255, 210),
//                shape = RoundedCornerShape(4.dp)
//            ) {
//                Text(
//                    text = "value: 10 + ${item}${System.lineSeparator()}" +
//                            "path: C:/111/222/333/444${System.lineSeparator()}" +
//                            "line: 37${System.lineSeparator()}" +
//                            "string: ObjectTemplate.damage 10",
//                    modifier = Modifier.padding(10.dp)
//                )
//            }
//        },
//        modifier = Modifier.padding(start = 40.dp),
//        delayMillis = 400, // in milliseconds
//        tooltipPlacement = TooltipPlacement.CursorPoint(
//            alignment = Alignment.BottomEnd,
//            offset = DpOffset((0).dp, (15).dp)
////            offset = if (index % 2 == 0) DpOffset((-16).dp, 2.dp) else DpOffset.Zero // tooltip offset
//        ),
//        content
//    )
//}
