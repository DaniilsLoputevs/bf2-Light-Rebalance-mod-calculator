//package io.github.bf2_mod_calculator.gui
//
//import androidx.compose.animation.AnimatedVisibility
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.interaction.MutableInteractionSource
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.material.ExperimentalMaterialApi
//import androidx.compose.ui.Modifier
//
//import androidx.compose.foundation.border
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.Divider
//import androidx.compose.material.MaterialTheme
//import androidx.compose.material.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.geometry.Size
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//
//
//val animals = listOf(
//    "Lion",
//    "Tiger",
//    "Leopard",
//    "Cheetah",
//    "Giraffe",
//    "Elephant",
//    "Zebra",
//    "Kangaroo",
//)
//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//fun AutoComplete(
//    animals: List<String>
//) {
//    val category by remember { mutableStateOf("") }
//    val textFieldSize by remember { mutableStateOf(Size.Zero) }
//    val expanded by remember { mutableStateOf(false) }
//    val interactionSource = remember { MutableInteractionSource() }
//
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable(
//                interactionSource = interactionSource,
//                indication = null,
//                onClick = {
//                    expanded = false
//                }
//            )
//    ) {
//        Text(
//            modifier = Modifier.padding(start = 3.dp, bottom = 2.dp),
//            text = "Animals",
//            fontSize = 16.sp,
//            color = Color.Black,
//            fontWeight = FontWeight.Medium
//        )
//
//        Column(modifier = Modifier.fillMaxWidth()) {
//            Row(modifier = Modifier.fillMaxWidth()) {
//                AutoCompleteTextField(
//                    value = category,
//                    onValueChange = {
//                        category = it
//                        expanded = true
//                    },
//                    placeholder = "Enter any Animal Name",
//                    animals = animals,
//                    textFieldSize = { size -> textFieldSize = size }
//                ) {
//                    expanded = !expanded
//                }
//            }
//
//            AnimatedVisibility(visible = expanded) {
//                AutoCompleteDropdown(
//                    modifier = Modifier.padding(horizontal = 5.dp),
//                    textFieldWidth = textFieldSize.width.dp,
//                    items = if (category.isNotEmpty()) {
//                        animals.filter {
//                            it.lowercase().contains(category.lowercase())
//                        }.sorted()
//                    } else {
//                        animals.sorted()
//                    }
//                ) { item ->
//                    category = item
//                    expanded = false
//                }
//            }
//        }
//    }
//}