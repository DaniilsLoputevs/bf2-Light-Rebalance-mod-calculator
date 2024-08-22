package io.github.bf2_mod_calculator

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun DropdownWithFilter() {
    var text by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val allOptions = listOf("Apple", "Banana", "Orange", "Grapes", "Pineapple")
    val filteredOptions = remember(text) {
        allOptions.filter { it.contains(text, ignoreCase = true) }
    }
    val focusManager = LocalFocusManager.current

    Column(modifier = Modifier.padding(16.dp)) {
        Box {
            BasicTextField(
                value = text,
                onValueChange = {
                    text = it
                    expanded = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { expanded = true }
                    .border(1.dp, Color.Blue)
                    .padding(8.dp),
                textStyle = LocalTextStyle.current.copy(color = Color.Blue),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus(true)
                    expanded = false
                })
            )
            if (text.isEmpty()) {
                Text(
                    text = "Enter text",
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(8.dp)
                )
            }
        }

        DropdownMenu(
            expanded = expanded && filteredOptions.isNotEmpty(),
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            filteredOptions.forEach { option ->
                DropdownMenuItem(onClick = {
                    text = option
                    expanded = false
                    focusManager.clearFocus(true)
                }) {
                    Text(text = option)
                }
            }
        }
    }
}

@Preview()
@Composable
fun DropdownWithFilterPreview() {
    DropdownWithFilter()
}
