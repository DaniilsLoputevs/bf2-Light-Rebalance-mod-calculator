package io.github.bf2_mod_calculator.main_page

import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.bf2_mod_calculator.compose.OnlyNumberTextField
import io.github.bf2_mod_calculator.models.GunTableContentLine
import java.math.BigDecimal


@Composable fun GunTable(guns: Iterable<GunTableContentLine>) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        // Заголовки столбцов
        Row(
//            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Header("Оружие")
            Header("Скорость Пули")
            Header("Коэф. гравитации")
            Header("Урон")
            Header("Мин. Урон")
            Header("Дистанция макс. урона (Снижение урона с, м)")
            Header("Дистанция мин. урона (Минимальный урон с, м:)")
            Header("Снижение урона за м:")
            Header("Дистанция")
            Header("Урон")
        }
        HorizontalDivider(thickness = 1.dp, color = Color.Gray)

        // Вывод строк таблицы
        guns.forEach { gun ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val defaultDistance = remember { 100.toBigDecimal() }
                var reducedDamage by remember { mutableStateOf(gun.calculateReducedDamage(defaultDistance)) }

                Cell(gun.gunName)
                Cell(gun.bulletSpeedFromGun.toPlainString())
                Cell(gun.gravityCoefficient.toPlainString())
                Cell(gun.baseDamage.toPlainString())
                Cell(gun.minDamage.toPlainString())
                Cell(gun.distanceThenReduceDamageStart_meters.toPlainString())
                Cell(gun.distanceThenReducedDamageIsMinumal_meters.toPlainString())
                Cell(gun.damageReducePerMeter.toPlainString()) // calculated by formula cell value
                DistanceCell(defaultDistance) { updatedDistance -> // user input value
                    reducedDamage = gun.calculateReducedDamage(updatedDistance)
                    println("gun=$gun | updatedDistance=$updatedDistance | reducedDamage=$reducedDamage")
                }
                Cell(reducedDamage.toPlainString()) // calculated by formula cell value

            }
        }
    }
}

//// TODO : move to style.kt
//@Composable fun ColumnScope.columnHeaderStyle() = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
//
private val RowScope.headerStyle: Modifier get() = Modifier.weight(1f)

@Composable private fun RowScope.Header(text: String) {
    Text(text = text, modifier = this.headerStyle)
//    VerticalDivider(thickness = 1.dp, color = Color.Gray)
}

@Composable private fun RowScope.Cell(text: String) {
    Text(text = text, modifier = this.headerStyle)
}

@Composable private fun RowScope.DistanceCell(value: BigDecimal, onValueChange: (BigDecimal) -> Unit) =
    OnlyNumberTextField(
        value = value,
        modifier = Modifier.weight(1f),
        onValueUpdated = onValueChange
    )

private fun GunTableContentLine.calculateReducedDamage(distanceInMeters: BigDecimal): BigDecimal {
    val calculatedDamage: BigDecimal =
        baseDamage - (distanceInMeters - distanceThenReduceDamageStart_meters) * damageReducePerMeter
    return minDamage.max(baseDamage.min(calculatedDamage))
    //TODO - test it
}
