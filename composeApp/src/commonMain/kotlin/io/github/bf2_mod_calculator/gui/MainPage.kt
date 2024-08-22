//package io.github.bf2_mod_calculator.gui
//
//import androidx.compose.foundation.*
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.lazy.rememberLazyListState
//import androidx.compose.material.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import io.github.bf2_mod_calculator.models.Calibre
//import io.github.bf2_mod_calculator.models.MainTableContent
//
//
//@Composable
////fun MainPage(calibers : List<String>, ammoMap : Map<String, GunNames>) {
//fun MainPage(content : MainTableContent) {
//    var selectedCalibre by remember { mutableStateOf<String?>(null) }
//    var selectedAmmo by remember { mutableStateOf<String?>(null) }
//    Box {
//        Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
//
//
//            // Колонка Калибра
//            Column(modifier = Modifier.weight(1f).padding(end = 8.dp)) {
//                content.calibreWithCartridges.keys.forEach { caliber ->
//                    Text(
//                        text = caliber,
//                        color = if (caliber == selectedCalibre) Color.Blue else Color.Black,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .clickable {
//                                selectedCalibre = caliber
////                                selectedAmmo = null // сброс выбранного элемента в правой колонке
//                            }
//                            .border(1.dp, Color.Blue)
//                            .padding(8.dp)
//                    )
//                }
//            }
//
//            // Колонка Снаряда
//            Column(modifier = Modifier.weight(1f).padding(end = 8.dp)) {
//                selectedCalibre?.let { selectedItem ->
//                    content.calibreWithCartridges[selectedItem]?.forEachIndexed { index, ammo ->
//                            Text(
//                                text = ammo,
//                                color = if (ammo == selectedCalibre) Color.Blue else Color.Black,
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .clickable {
//                                        selectedAmmo = caliber
//                                        selectedAmmo = null // сброс выбранного элемента в правой колонке
//                                    }
//                                    .border(1.dp, Color.Blue)
//                                    .padding(8.dp)
//                            )
//                    }
//                }
//            }
//
//
//            Column(modifier = Modifier.weight(1f)) {
//                selectedCalibre?.let { selectedItem ->
//                    content.calibreWithCartridges[selectedItem]?.forEachIndexed { index, ammo ->
//                        warpWithTooltipTextAre(index, ammo) {
//                            Text(
//                                text = ammo,
//                                color = if (ammo == selectedAmmo) Color.Blue else Color.Black,
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .clickable {
//                                        selectedAmmo = ammo
//                                    }
//                                    .padding(8.dp)
//                            )
//                        }
//
//
//                    }
//                }
//            }
//        }
//    }
//}
//
//
//
//@Composable
//fun LazyScrollableCalibreColumn(calibres : List<Calibre>) {
//    Box(
//        modifier = Modifier.fillMaxSize()
//            .background(color = Color(180, 180, 180))
//            .padding(10.dp)
//    ) {
//
//        val state = rememberLazyListState()
//
//        LazyColumn(Modifier.fillMaxSize().padding(end = 12.dp), state) {
////            key()
//            items(calibres) {
//                TextBox("Item #$it")
//                Spacer(modifier = Modifier.height(5.dp))
//            }
//        }
//        VerticalScrollbar(
//            modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
//            adapter = rememberScrollbarAdapter(
//                scrollState = state
//            )
//        )
//    }
//}
//
//
//
//@Composable
//fun TextBox(text: String = "Item") {
//    Box(
//        modifier = Modifier.height(32.dp)
//            .fillMaxWidth()
//            .background(color = Color(0, 0, 0, 20))
//            .padding(start = 10.dp),
//        contentAlignment = Alignment.CenterStart
//    ) {
//        Text(
//            text = caliber,
//            color = if (caliber == selectedCalibre) Color.Blue else Color.Black,)
//    }
//}