package io.github.bf2_mod_calculator.gui

//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.ui.tooling.preview.Preview
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

//import com.mgvautosearchtextfield.ui.theme.MgvAutoSearchTextFieldTheme

val animals = listOf(
    "Lion",
    "Tiger",
    "Leopard",
    "Cheetah",
    "Giraffe",
    "Elephant",
    "Zebra",
    "Kangaroo",
    "Koala",
    "Panda",
    "Gorilla",
    "Hippopotamus",
    "Rhinoceros",
    "Orangutan",
    "Polar Bear",
    "Grizzly Bear",
    "Sloth",
    "Kangaroo",
    "Koala",
    "Panda",
    "Gorilla",
    "Hippopotamus",
    "Rhinoceros",
    "Orangutan",
    "Polar Bear",
    "Grizzly Bear",
    "Sloth",
    "Kangaroo",
    "Koala",
    "Panda",
    "Gorilla",
    "Hippopotamus",
    "Rhinoceros",
    "Orangutan",
    "Polar Bear",
    "Grizzly Bear",
    "Sloth",
    "Kangaroo",
    "Koala",
    "Panda",
    "Gorilla",
    "Hippopotamus",
    "Rhinoceros",
    "Orangutan",
    "Polar Bear",
    "Grizzly Bear",
    "Sloth"
)


@Composable
fun AutoComplete() {




    var category by remember { mutableStateOf("") }
    val heightTextFields by remember { mutableStateOf(55.dp) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    var expanded by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = Modifier
            .padding(30.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = { expanded = false }
            )
    ) {
        Text(
            modifier = Modifier.padding(start = 3.dp, bottom = 2.dp),
            text = "Animals",
            fontSize = 16.sp,
            color = Color.Black,
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
                    value = category,
                    onValueChange = {
                        category = it
                        expanded = true
                    },
                    placeholder = { Text("Enter any Animals Name") },
////                    colors = TextFieldDefaults.colors(
//
////                    colors = TextFieldDefaults.textFieldColors(
//                        containerColor = Color.Transparent,
//                        focusedIndicatorColor = Color.Transparent,
//                        unfocusedIndicatorColor = Color.Transparent,
//                        cursorColor = Color.Black
//                    ),
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
                        IconButton(onClick = { expanded = !expanded }) {
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

            AnimatedVisibility(visible = expanded) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .width(textFieldSize.width.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    LazyColumn(
                        modifier = Modifier.heightIn(max = 150.dp),
                    ) {
                        val suggestions = when (category.isNotEmpty()) {
                            true -> animals
                                .filter { animal ->
                                    animal.lowercase()
                                        .let { it.contains(category.lowercase()) || it.contains("others") }
                                }
                                .distinct()
                                .sorted()

                            false -> animals.sorted()
                                .distinct()
                        }
                        items(suggestions) { animal ->
                            ItemsCategory(title = animal) { title ->
                                category = title
                                expanded = false
                            }
                        }

                    }
                }
            }
        }
    }
}


@Composable private fun ItemsCategory(title: String, onSelect: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onSelect(title)
            }
            .padding(10.dp)
    ) {
        Text(text = title, fontSize = 16.sp)
    }
}