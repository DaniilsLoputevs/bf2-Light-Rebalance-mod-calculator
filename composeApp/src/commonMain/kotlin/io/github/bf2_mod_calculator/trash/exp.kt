package io.github.bf2_mod_calculator.trash


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import io.github.windedge.table.DataTable


@Composable
fun aaa() {


    val data = listOf(
        mapOf("Name" to "John Doe", "Age" to "30", "Email" to "john.doe@example.com"),
        mapOf("Name" to "Jane Doe", "Age" to "25", "Email" to "jane.doe@example.com")
    )

    DataTable(
        columns = {
            headerBackground {
                Box(modifier = Modifier.background(color = Color.LightGray))
            }
            column { Text("Name") }
            column { Text("Age") }
            column { Text("Email") }
        }
    ) {
        data.forEach { record ->
            row(modifier = Modifier) {
                cell { Text(record["Name"] ?: "") }
                cell { Text(record["Age"] ?: "") }
                cell { Text(record["Email"] ?: "") }
            }
        }
    }
}