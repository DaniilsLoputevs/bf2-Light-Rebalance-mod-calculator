//package io.github.bf2_mod_calculator.gui
//
//import androidx.compose.foundation.border
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.MaterialTheme
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.MutableState
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//
//@Composable
//fun MainStub() {
//    val leftColumnItems = listOf("Category 1", "Category 2", "Category 3")
//    val rightColumnItems = mapOf(
//        "Category 1" to listOf("Item 1.1", "Item 1.2", "Item 1.3"),
//        "Category 2" to listOf("Item 2.1", "Item 2.2", "Item 2.3"),
//        "Category 3" to listOf("Item 3.1", "Item 3.2", "Item 3.3")
//    )
//    val selectedLeftItem = remember { mutableStateOf<String?>(null) }
//    val selectedRightItem = remember { mutableStateOf<String?>(null) }
//    Box {
//        Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
//            firstColumn(leftColumnItems, selectedLeftItem)
//            secondColumn(rightColumnItems, selectedLeftItem, selectedRightItem)
//        }
//    }
//}
//
//@Composable private fun RowScope.firstColumn(columnContent: List<String>, selectedItem: MutableState<String?>) {
//    Column(modifier = Modifier.weight(1f).padding(end = 8.dp).border(1.dp, Color.Yellow)) {
//        ColumnHeader("First Col")
//        columnContent.forEach { item ->
//            Text(
//                text = item,
//                color = if (item == selectedItem.value) Color.Blue else Color.Black,
//                modifier = selectableColumnContentStyle {
//                    selectedItem.value = item
//                }
//            )
//        }
//    }
//}
//
//@Composable fun RowScope.secondColumn(
//    columnContent: Map<String, List<String>>,
//    prevSelectedItem: MutableState<String?>,
//    selectedItem: MutableState<String?>
//) {
//    Column(modifier = Modifier.weight(1F).padding(end = 8.dp).border(1.dp, Color.Yellow)) {
//        ColumnHeader("Second Col")
//        prevSelectedItem.value.let { selectedItemValue ->
//            columnContent[selectedItemValue]?.forEachIndexed { index, item ->
//                warpWithTooltipTextAre(index, item) {
//                    Text(
//                        text = item,
//                        color = if (item == selectedItem.value) Color.Blue else Color.Black,
//                        modifier = selectableColumnContentStyle {
//                            selectedItem.value = item
//                        }
//                    )
//                }
//
//
//            }
//        }
//    }
//
//}
//
//// TODO : move to style.kt
//@Composable fun ColumnScope.columnHeaderStyle() = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
//
//
//@Composable fun ColumnScope.selectableColumnContentStyle(
//    onClick: () -> Unit
//) = Modifier
//    .fillMaxWidth()
//    .border(1.dp, Color.Blue)
//    .padding(8.dp)
//    .clickable(onClick = onClick)
//
//@Composable fun ColumnScope.ColumnHeader(text: String) = Text(
//    text = text,
//    style = columnHeaderStyle(),
//    modifier = Modifier.padding(8.dp)
//)
