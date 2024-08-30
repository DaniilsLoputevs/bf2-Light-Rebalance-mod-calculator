package io.github.bf2_mod_calculator.parser

import io.github.bf2_mod_calculator.models.CsvRow
import io.github.bf2_mod_calculator.models.GunTableContent
import io.github.bf2_mod_calculator.models.GunTableContentLine

// TODO : make csv parser & config scanner & remove this hardcode
val mainPageContent = GunTableContent(
    cartridgesForCalibre = mapOf(
        "7.62x51" to listOf(
            "SubSon",
            "M62",
            "M80",
            "M118",
            "M118LR"
        ),
        "8.6x70" to listOf(
            "PFOBP",
            "T5000"
        )
    ),
    tableLinesForAmmo = mapOf(
        "SubSon" to listOf(
            GunTableContentLine(
                gunName = "AAA",
                bulletSpeedFromGun = 170.0,
                gravityCoefficient = 2.2,
                baseDamage = 89.0,
                minDamage = 3.0,
                distanceThenReduceDamageStart_meters = 5.0,
                distanceThenReducedDamageIsMinumal_meters = 85.0
            ),
            GunTableContentLine(
                gunName = "BBB",
                bulletSpeedFromGun = 170.0,
                gravityCoefficient = 2.2,
                baseDamage = 89.0,
                minDamage = 3.0,
                distanceThenReduceDamageStart_meters = 5.0,
                distanceThenReducedDamageIsMinumal_meters = 85.0
            )
        ),
        "M62" to listOf(
            GunTableContentLine(
                gunName = "CCC",
                bulletSpeedFromGun = 511,
                gravityCoefficient = 0,
                baseDamage = 0,
                minDamage = 1,
                distanceThenReduceDamageStart_meters = 65,
                distanceThenReducedDamageIsMinumal_meters = 93

            ),
            GunTableContentLine(
                gunName = "DDD",
                bulletSpeedFromGun = 481,
                gravityCoefficient = 1,
                baseDamage = 6,
                minDamage = 92,
                distanceThenReduceDamageStart_meters = 8,
                distanceThenReducedDamageIsMinumal_meters = 10,
            )

        )
    )
)

class MainTableContentProvider {

    fun content(): GunTableContent {
        val csvLines = parseCsv()

//        val calibres = csvLines.asSequence().map { it.calibre }.toSet()
        val cartridges = csvLines.groupBy({ it.calibre }, { it.ammo })
        val mainTableContentLines = csvLines.groupBy({ it.ammo }, ::toMainTableContentLines)

        return GunTableContent(
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
            CsvRow(
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

    private fun toMainTableContentLines(it: CsvRow): GunTableContentLine =
        GunTableContentLine(
            gunName = it.gunName,
            bulletSpeedFromGun = it.bulletSpeedFromGun.toBigDecimal(),
            gravityCoefficient = it.gravityCoefficient.toBigDecimal(),
            baseDamage = it.baseDamage.toBigDecimal(),
            minDamage = it.minDamage.toBigDecimal(),
            distanceThenReduceDamageStart_meters = it.distanceThenReduceDamageStart_meters.toBigDecimal(),
            distanceThenReducedDamageIsMinumal_meters = it.distanceThenReducedDamageIsMinumal_meters.toBigDecimal(),
        )

}


