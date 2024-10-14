package io.github.bf2_mod_calculator.trash.gui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.seanproctor.datatable.DataColumn
import com.seanproctor.datatable.material3.DataTable
import io.github.bf2_mod_calculator.compose.AutoCompleteTextField
import io.github.bf2_mod_calculator.compose.warpWithTooltipTextAre

data class MyData(
    val field1: String,
    val field2: String,
    val field3: String,
    val field4: String,
    val field5: String
)

val dataList = listOf(
    MyData("Row 1, Field 1", "Row 1, Field 2", "Row 1, Field 3", "Row 1, Field 4", "Row 1, Field 5"),
    MyData("Row 2, Field 1", "Row 2, Field 2", "Row 2, Field 3", "Row 2, Field 4", "Row 2, Field 5"),
    MyData("Row 3, Field 1", "Row 3, Field 2", "Row 3, Field 3", "Row 3, Field 4", "Row 3, Field 5")
    // Добавьте больше объектов по мере необходимости
)

@Composable fun MainStub() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {

        var selectedRow by remember { mutableStateOf<Int?>(null) }
        DataTable(
            columns = listOf(
                DataColumn {
                    Text("Header A")
                },
                DataColumn {
                    Text("Header B")
                },
                DataColumn(alignment = Alignment.End) {
                    Text("Header C")
                },
            )
        ) {
            row {
                onClick = { selectedRow = 0 }
                cell { Text("Cell A1") }
                cell { Text("Cell B1") }
                cell { Text("Cell C1") }
            }
            row {
                onClick = { selectedRow = 1 }
                cell { Text("Cell A2") }
                cell { Text("Cell B2") }
                cell { Text("Cell C2") }
            }
        }


//        AutoComplete()


//        AutoCompleteTextField(suggestions = animals, inputTitle = "Animal 1", placeholder = "Enter animal name") {
//            println("print = $it")
//        }


        // Заголовки столбцов
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Field 1", modifier = Modifier.weight(1f))
            Text("Field 2", modifier = Modifier.weight(1f))
            Text("Field 3", modifier = Modifier.weight(1f))
            Text("Field 4", modifier = Modifier.weight(1f))
            Text("Field 5", modifier = Modifier.weight(1f))
        }

        Divider(color = Color.Gray, thickness = 1.dp)

        // Выводим строки таблицы для каждого объекта
        dataList.forEach { data ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(data.field1, modifier = Modifier.weight(1f))
                Text(data.field2, modifier = Modifier.weight(1f))
                Text(data.field3, modifier = Modifier.weight(1f))
                Text(data.field4, modifier = Modifier.weight(1f))
                Text(data.field5, modifier = Modifier.weight(1f))
            }
            Divider(color = Color.Gray, thickness = 1.dp)
        }
    }
}


val stubContent = listOf("a", "b", "c", "d")

@Composable fun MainStub1() {
    val leftColumnItems = listOf("Category 1", "Category 2", "Category 3")
    val rightColumnItems = mapOf(
        "Category 1" to listOf("Item 1.1", "Item 1.2", "Item 1.3"),
        "Category 2" to listOf("Item 2.1", "Item 2.2", "Item 2.3"),
        "Category 3" to listOf("Item 3.1", "Item 3.2", "Item 3.3")
    )


    val selectedLeftItem = remember { mutableStateOf<String?>(null) }
    val selectedRightItem = remember { mutableStateOf<String?>(null) }
    Box {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
//            firstColumn(leftColumnItems, selectedLeftItem)
//            secondColumn(rightColumnItems, selectedLeftItem, selectedRightItem)


//            Divider(color = Color.Green, thickness = 5.dp)
//            Column {
//                Row { Text("1") }
//                Row { Text("2") }
//                Row { Text("3") }
//            }
//            Column {
//                Row { Text("4") }
//                Row { Text("5") }
//                Row { Text("6") }
//            }

            Row {
                Column { Text("a1") }
                Column { Text("b1") }
                Column { Text("c1") }
            }
            Row {
                Column { Text("a2") }
                Column { Text("b2") }
                Column { Text("c2") }
            }
            Row {
                Column { Text("a3") }
                Column { Text("b3") }
                Column { Text("c3") }
            }

//            Box {
//                Row {
//                    colStub()
//                    colStub()
//                    colStub()
//                    colStub()
//                    colStub()
//                    colStub()
//                    colStub()
//                    colStub()
//                    colStub()
//                    colStub()
//                }
//            }

        }
    }
}

var count = 1

@Composable fun RowScope.colStub() {
    Column(modifier = Modifier.weight(1f).padding(end = 8.dp).border(1.dp, Color.Yellow)) {
        ColumnHeader("First Col${count++}")
        stubContent.forEach { item ->
            Text(
                text = item,
//                        color = if (item == selectedItem.value) Color.Blue else Color.Black,
                modifier = selectableColumnContentStyle()
            )
        }
    }
}

@Composable private fun RowScope.firstColumn(columnContent: List<String>, selectedItem: MutableState<String?>) {
    Column(modifier = Modifier.weight(1f).padding(end = 8.dp).border(1.dp, Color.Yellow)) {
        ColumnHeader("First Col")
        columnContent.forEach { item ->
            Text(
                text = item,
                color = if (item == selectedItem.value) Color.Blue else Color.Black,
                modifier = selectableColumnContentStyle {
                    selectedItem.value = item
                }
            )

        }
    }
}

@Composable fun RowScope.secondColumn(
    columnContent: Map<String, List<String>>,
    prevSelectedItem: MutableState<String?>,
    selectedItem: MutableState<String?>
) {
    Column(modifier = Modifier.weight(1F).padding(end = 8.dp).border(1.dp, Color.Yellow)) {
        ColumnHeader("Second Col")
        prevSelectedItem.value.let { selectedItemValue ->
            columnContent[selectedItemValue]?.forEachIndexed { index, item ->
                warpWithTooltipTextAre(index, item) {
                    Text(
                        text = item,
                        color = if (item == selectedItem.value) Color.Blue else Color.Black,
                        modifier = selectableColumnContentStyle {
                            selectedItem.value = item
                        }
                    )
                }


            }
        }
    }

}

// TODO : move to style.kt
@Composable fun ColumnScope.columnHeaderStyle() = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)


@Composable fun ColumnScope.selectableColumnContentStyle(
    onClick: () -> Unit = {}
) = Modifier
    .fillMaxWidth()
    .border(1.dp, Color.Blue)
    .padding(8.dp)
    .clickable(onClick = onClick)

@Composable fun ColumnScope.ColumnHeader(text: String) = Text(
    text = text,
    style = columnHeaderStyle(),
    modifier = Modifier.padding(8.dp)
)
