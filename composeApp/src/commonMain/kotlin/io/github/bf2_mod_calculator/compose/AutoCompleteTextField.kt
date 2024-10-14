package io.github.bf2_mod_calculator.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
    value: String = "",
    suggestions: Iterable<String>,
    inputLabel: String,
    placeholder: String = "",
    enable: Boolean = true,
    onSelect: (String) -> Unit
) {
    var currentTextFieldValue by remember { mutableStateOf(value) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    var isExpanded by remember { mutableStateOf(false) }
//    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = Modifier
            .padding(30.dp)
    ) {
        Text(
            modifier = Modifier.padding(start = 3.dp, bottom = 2.dp),
            text = inputLabel,
            fontSize = 16.sp,
            color = if (enable) Color.Black else Color.Gray,
            fontWeight = FontWeight.Medium
        )

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
                    }
//                        .onFocusChanged { focusState ->
////                            println("focus state is ${focusState}")
//                            isExpanded = focusState.isFocused
//                        }
                ,

                value = currentTextFieldValue,
                onValueChange = {
                    currentTextFieldValue = it
                    isExpanded = true
                },
//                    onValueChange = {
//                        // TODO - invoke business logic
//                        // TODO: если новый текст соответствует одному из пунктов выбора, то подтвердить выбор
//                        textMutableState.value = it
////                        textMutableState = it
////                        currentTextFieldValue.setValue(null, STUB::name, it)
//                        isExpanded = true
//
//                        ifTextContainsInSuggestionsWriteToSelectContainer(textMutableState.value, suggestions, selectedItemContainer)
////                        ifTextContainsInSuggestionsWriteToSelectContainer(textMutableState, suggestions, selectedItemContainer)
//                    },

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
//                    keyboardActions = KeyboardActions(
//                        onDone = {
//                            // TODO - invoke business logic
//                            // set value
//
//                            ifTextContainsInSuggestionsWriteToSelectContainer(textMutableState.value, suggestions, selectedItemContainer)
////                            ifTextContainsInSuggestionsWriteToSelectContainer(textMutableState, suggestions, selectedItemContainer)
//                        },
//                    ),
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
                // Состояние скролла
//                    val scrollState = rememberScrollState()

                // TODO: при возможности добавить ВИДИМОСТЬ скроллбару
                LazyColumn(
                    modifier = Modifier.heightIn(max = 150.dp)
//                            .verticalScroll(scrollState)
                ) {
                    val remainSuggestions = when (currentTextFieldValue.isEmpty()) {
                        true -> suggestions.sorted().distinct()
                        false -> suggestions
                            .filter { suggestion ->
                                suggestion.lowercase().contains(currentTextFieldValue.lowercase())
                            }
                            .distinct()
                            .sorted()
                    }

                    items(remainSuggestions) { suggestion ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    println("item.clickable($suggestion)")
                                    currentTextFieldValue = suggestion
                                    isExpanded = false
                                    onSelect(suggestion) // вызов лямбды с бизнес логикой
                                }
                                .padding(10.dp)
                        ) {
                            Text(
                                text = suggestion, fontSize = 16.sp, modifier = Modifier
//                                    .clickable{ println("text click=$suggestion") }
                            )
                        }
                    }
                }

//                    VerticalScrollbar(
//                        adapter = rememberScrollbarAdapter(scrollState),
//                        modifier = Modifier.fillMaxHeight()
//                    )

            }
        }
    }
}

//private class AutoCompleteTextFieldViewModel : ViewModel()
