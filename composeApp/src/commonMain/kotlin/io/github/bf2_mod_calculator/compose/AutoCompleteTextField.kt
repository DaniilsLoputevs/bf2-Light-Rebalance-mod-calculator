package io.github.bf2_mod_calculator.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize

/**
 * copy & then adapted from: [GitHub: MaheshaGubbi - mgv_auto_search_text](https://github.com/MaheshaGubbi/mgv_auto_search_textfield)
 */
@Composable fun AutoCompleteTextField(
    suggestions: Iterable<String>,
    inputTitle: String,
    placeholder: String = "",
    enable : Boolean = true,
    onSelect: (String) -> Unit
) {
    var currentTextFieldValue by remember { mutableStateOf("") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    var isExpanded by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = Modifier
            .padding(30.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = { isExpanded = false }
            )
    ) {
        Text(
            modifier = Modifier.padding(start = 3.dp, bottom = 2.dp),
            text = inputTitle,
            fontSize = 16.sp,
            color = if (enable) Color.Black else Color.Gray,
            fontWeight = FontWeight.Medium
        )

        Column(modifier = Modifier) {
            Row(modifier = Modifier) {
                TextField(
                    modifier = Modifier
                        .height(55.dp)
                        .border(
                            width = 1.8.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(15.dp)
                        )
                        .onGloballyPositioned { coordinates ->
                            textFieldSize = coordinates.size.toSize()
                        },
                    value = currentTextFieldValue,
                    onValueChange = {
                        currentTextFieldValue = it
                        isExpanded = true
                    },
                    placeholder = { Text(placeholder) },
                    enabled = enable,
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    singleLine = true,
                    trailingIcon = {
                        IconButton(onClick = { isExpanded = !isExpanded }) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                imageVector = Icons.Rounded.KeyboardArrowDown,
                                contentDescription = "arrow",
                                tint = Color.Black
                            )
                        }
                    }
                )
            }

            AnimatedVisibility(visible = isExpanded) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .width(textFieldSize.width.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    LazyColumn(
                        modifier = Modifier.heightIn(max = 150.dp),
                    ) {
                        val remainSuggestions = when (currentTextFieldValue.isNotEmpty()) {
                            true -> suggestions
                                .filter { animal ->
                                    animal.lowercase()
                                        .let { it.contains(currentTextFieldValue.lowercase()) || it.contains("others") }
                                }
                                .distinct()
                                .sorted()

                            false -> suggestions.sorted()
                                .distinct()
                        }
                        items(remainSuggestions) { suggestion ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        currentTextFieldValue = suggestion
                                        isExpanded = false
                                        onSelect(suggestion) // вызов лямбды с бизнес логикой
                                    }
                                    .padding(10.dp)
                            ) {
                                Text(text = suggestion, fontSize = 16.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}
