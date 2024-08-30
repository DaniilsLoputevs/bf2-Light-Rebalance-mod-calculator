package io.github.bf2_mod_calculator.trash

import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import io.github.bf2_mod_calculator.compose.OnlyNumberTextField
import java.math.BigDecimal

// Данные для таблицы
data class GunData(
    val gunName: String,
    val damage: BigDecimal,
    var distance: TextFieldValue,
    var damageOnDistance: BigDecimal
)

@Composable
fun GunDataTable(content: List<GunData>) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        // Заголовки столбцов
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Gun name", modifier = Modifier.weight(1f))
            Text("Damage", modifier = Modifier.weight(1f))
            Text("Damage on distance", modifier = Modifier.weight(1f))
            Text("Distance (m)", modifier = Modifier.weight(1f))
        }

        HorizontalDivider(thickness = 1.dp, color = Color.Gray)

        // Вывод строк таблицы
        content.forEachIndexed { index, gunData ->

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                var component_distanceDamage by remember { mutableStateOf(100.toBigDecimal()) }

                Text(gunData.gunName, modifier = Modifier.weight(1f))
                Text(gunData.damage.toPlainString(), modifier = Modifier.weight(1f))
//                Text(gunData.damageOnDistance.toPlainString(), modifier = Modifier.weight(1f))
                Text(component_distanceDamage.toPlainString(), modifier = Modifier.weight(1f))


                // EditableTextField для ввода расстояния
                OnlyNumberTextField(
                    value = 100F,
                    modifier = Modifier.weight(1f),
                    onValueUpdated = { newValue ->
                        println("edit: newValue=$newValue")
//                        gunData.distance = newValue
                        // Логика для обновления значения Damage on distance
//                    gunData.damageOnDistance = calculateDamageOnDistance(gunData.damage, newValue.text)
                        gunData.damageOnDistance = calculateDamageOnDistance(gunData.damage, newValue.toPlainString())
                        component_distanceDamage = gunData.damageOnDistance

                    })


//            TextField(
//                value = gunData.distance,
//                onValueChange = { newValue ->
//                    println("edit: newValue=$newValue")
//                    gunData.distance = newValue
//                    // Логика для обновления значения Damage on distance
//                    gunData.damageOnDistance = calculateDamageOnDistance(gunData.damage, newValue.text)
//                    component_distanceDamage = gunData.damageOnDistance
//
//                },
//                modifier = Modifier.weight(1f)
//            )
            }
            HorizontalDivider(thickness = 1.dp, color = Color.Gray)
        }
    }
}


// Функция для расчета Damage on distance
fun calculateDamageOnDistance(damage: BigDecimal, distance: String): BigDecimal {
    return if (distance.isNotEmpty()) {
        val distanceValue = distance.toBigDecimalOrNull() ?: BigDecimal.ZERO
        // Простая формула, например: урон уменьшается пропорционально расстоянию
        damage / (BigDecimal.ONE + distanceValue / BigDecimal(100))
    } else {
        damage
    }
}

@Composable
fun MyApp() {
    val gunDataList = remember {
        mutableStateListOf(
            GunData("AK-47", BigDecimal(100), TextFieldValue("10"), BigDecimal(95)),
            GunData("M4A1", BigDecimal(90), TextFieldValue("20"), BigDecimal(80)),
            GunData("Sniper", BigDecimal(150), TextFieldValue("50"), BigDecimal(130))
            // Добавьте больше объектов по мере необходимости
        )
    }

    GunDataTable(content = gunDataList)
}
