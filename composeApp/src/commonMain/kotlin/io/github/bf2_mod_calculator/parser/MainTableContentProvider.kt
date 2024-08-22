package io.github.bf2_mod_calculator.parser

import io.github.bf2_mod_calculator.models.CsvLine
import io.github.bf2_mod_calculator.models.MainTableContent
import io.github.bf2_mod_calculator.models.MainTableContentLine

class MainTableContentProvider {

    fun content(): MainTableContent {
        val csvLines = parseCsv()

//        val calibres = csvLines.asSequence().map { it.calibre }.toSet()
        val cartridges = csvLines.groupBy ({ it.calibre }, { it.ammo })
        val mainTableContentLines = csvLines.groupBy ({ it.ammo }, ::toMainTableContentLines)

        return MainTableContent(
//            calibres = calibres,
            cartridgesForCalibre = cartridges,
            tableLinesForAmmo = mainTableContentLines
        )
    }

    val csvLines = listOf(
        "Калибр;Патрон;Оружие;Скорость;Грав.Коэф;Урон;Мин.Урон;Снижение урона с, м:;Минимальный урон с, м:",
        "4.6x30;CPSS;MP-7;435,00;1,90;28,00;1,90;5,00;85,00;0,3;10000",
        "5.7x28;SS190;FN57;390,00;2,00;31,00;2,00;7,00;90,00;0,35;",
        "7.62x25;NOR;TYPE51;220,00;2,80;39,00;2,00;5,00;60,00;0,67;"
    )


    private fun parseCsv() = csvLines
        .asSequence()
        .map { line -> line.split(";") }
        .drop(1)
        .map {
            CsvLine(
                calibre = it[0],
                ammo = it[1],
                gunName = it[2],
                bulletSpeedFromGun = it[3],
                gravityCoefficient = it[4],
                baseDamage = it[5],
                minDamage = it[6],
                distanceThenReduceDamageStart_meters = it[7],
                distanceThenReducedDamageIsMinumal_meters = it[8],
            )
        }.toList()

    private fun toMainTableContentLines(it : CsvLine):MainTableContentLine =
        MainTableContentLine(
            gunName = it.gunName,
            bulletSpeedFromGun = it.bulletSpeedFromGun.toFloat(),
            gravityCoefficient = it.gravityCoefficient.toFloat(),
            baseDamage = it.baseDamage.toFloat(),
            minDamage = it.minDamage.toFloat(),
            distanceThenReduceDamageStart_meters = it.distanceThenReduceDamageStart_meters.toFloat(),
            distanceThenReducedDamageIsMinumal_meters = it.distanceThenReducedDamageIsMinumal_meters.toFloat(),
            )

}


