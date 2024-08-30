package io.github.bf2_mod_calculator.main_page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.*
import io.github.bf2_mod_calculator.compose.AutoCompleteTextField
import io.github.bf2_mod_calculator.parser.mainPageContent


@Composable fun MainPage() {
    Column {
        val calibreSuggestions = mainPageContent.cartridgesForCalibre.keys
        var selectedCalibre by remember { mutableStateOf("") }
//        var selectedCalibre by remember { mutableStateOf("7.62x51") } // develop & debug

        val ammoSuggestions by remember {
            derivedStateOf { mainPageContent.cartridgesForCalibre[selectedCalibre] ?: emptyList() }
        }
        var selectedAmmo by remember { mutableStateOf("") }
//        var selectedAmmo by remember { mutableStateOf("SubSon") } // develop & debug


        val ammoInputEnable by remember {
            derivedStateOf { selectedCalibre.isNotBlank() }
        }
//        val ammoInputEnable = true

        //TODO : починить выбор, сейчас 1 подпункт, а должно быть 2
        Row {
            AutoCompleteTextField(
                suggestions = calibreSuggestions,
                inputTitle = "Калибр",
                placeholder = "Начните печатать название Калибра..."
            ) {
                println("LOG: selectedCalibre = $it")
                selectedCalibre = it
            }

            // TODO : штука не выбирается при клике
            AutoCompleteTextField(
                value = selectedCalibre,
                suggestions = ammoSuggestions,
                inputTitle = "Боеприпас",
                placeholder = "Начните печатать название Боеприпаса...",
                enable = ammoInputEnable
            ) {
                println("LOG: selectedAmmo = $it")
                selectedAmmo = it
            }
        }

        GunTable(mainPageContent.tableLinesForAmmo[selectedAmmo] ?: emptyList())
    }
}
