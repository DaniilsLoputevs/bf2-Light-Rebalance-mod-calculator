package io.github.bf2_mod_calculator.main_page

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.bf2_mod_calculator.compose.AutoCompleteTextField
import io.github.bf2_mod_calculator.models.MainTableContent
import io.github.bf2_mod_calculator.models.MainTableContentLine


val mainPageContent = MainTableContent(
    cartridgesForCalibre = mapOf(
        "7.62x51" to listOf(
            "SubSon",
            "M62",
            "M80",
            "M118",
            "M118LR"
        )
    ),
    tableLinesForAmmo = mapOf(
        "SubSon" to listOf(
            MainTableContentLine(
                gunName = "AAA",
                bulletSpeedFromGun = 170F,
                gravityCoefficient = 0F,
                baseDamage = 0F,
                minDamage = 2F,
                distanceThenReduceDamageStart_meters = 2F,
                distanceThenReducedDamageIsMinumal_meters = 89F
            ),
            MainTableContentLine(
                gunName = "BBB",
                bulletSpeedFromGun = 170F,
                gravityCoefficient = 0F,
                baseDamage = 0F,
                minDamage = 2F,
                distanceThenReduceDamageStart_meters = 2F,
                distanceThenReducedDamageIsMinumal_meters = 89F
            )
        ),
        "M62" to listOf(
            MainTableContentLine(
                gunName = "CCC",
                511, 0, 0, 1, 65, 93

            ),
            MainTableContentLine(
                gunName = "DDD",
                481, 1, 6, 92, 8, 10,
            )

        )
    )
)


@Composable
fun MainPage() {
    Column {
        val calibreSuggestions = mainPageContent.cartridgesForCalibre.keys
        var selectedCalibre by remember { mutableStateOf("") }

        val ammoSuggestions = mainPageContent.cartridgesForCalibre[selectedCalibre] ?: emptyList()
        var selectedAmmo by remember { mutableStateOf("") }

        Row {
            AutoCompleteTextField(
                suggestions = calibreSuggestions,
                inputTitle = "Калибр",
                placeholder = "Начните печатать название Калибра..."
            ) {
                println("LOG: selectedCalibre = $it")
                selectedCalibre = it
            }

            AutoCompleteTextField(
                suggestions = ammoSuggestions,
                inputTitle = "Боеприпас",
                placeholder = "Начните печатать название Боеприпаса...",
                enable = selectedCalibre.isNotBlank()
            ) {
                println("LOG: selectedAmmo = $it")
                selectedAmmo = it
            }
        }
        MainTable(mainPageContent.tableLinesForAmmo[selectedAmmo] ?: emptyList())
    }
}

@Composable
fun MainTable(content: Iterable<MainTableContentLine>) {
    val tableModifier = Modifier
            .padding(50.dp)
            .verticalScroll(rememberScrollState())
            .border(width = 3.dp, color = Color.Cyan)

    Row() {
//    Row(modifier = tableModifier) {
        Text("Оружие", modifier = tableModifier)
        Text("Скорость Пули", modifier = tableModifier)
        Text("Коэф. гравитации", modifier = tableModifier)
        Text("Урон", modifier = tableModifier)
    }
    content.forEach {
        Row() {
//        Row(modifier = tableModifier) {
            Text(it.gunName, modifier = tableModifier)
            Text(it.bulletSpeedFromGun.toString(), modifier = tableModifier)
            Text(it.gravityCoefficient.toString(), modifier = tableModifier)
            Text(it.baseDamage.toString(), modifier = tableModifier)

        }
    }
}