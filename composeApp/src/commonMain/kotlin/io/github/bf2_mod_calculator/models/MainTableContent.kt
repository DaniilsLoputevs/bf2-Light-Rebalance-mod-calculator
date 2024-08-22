package io.github.bf2_mod_calculator.models

typealias Calibre = String
typealias Ammo = String

class MainTableContent(
//     calibres :  Set<Calibre>,
    val cartridgesForCalibre: Map<Calibre, List<Ammo>>,
    val tableLinesForAmmo: Map<Ammo, List<MainTableContentLine>>


//    val calibers: List<String>,
//    val calibreWithCartridges: Map<String, List<String>>,
//    val ammoWithTableLine: Map<String, List<MainTableLine>>

    //             calibre    cartridge[]       tablesLine[]
//    val data : Map<String, Map<String, List<MainTableLine>>>
)
//fun ma () {
//    val content = MainTableContent()
//
//    val calibre = content.data.entries.first()
//    val calibreName = calibre.key
//    val calibreAmmo: Map<String, List<MainTableContentLine>> = calibre.value
//
//    val ammo = calibreAmmo.entries.first()
//    val ammoName = ammo.key
//    val ammoInfoLines = ammo.value
//
//
//    // calibres :  List<Calibre>
//    // cartridgesForCalibre : Map<Calibre, List<Cartridge>>
//    // tableLinesForCartridge : Map<Cartridge, TableLine>
//}


class MainTableContentLine(
    val gunName: String,
    val bulletSpeedFromGun: Float = 0F,
    val gravityCoefficient: Float,
    val baseDamage: Float, // D |  Урон
    val minDamage: Float, // E |  Мин.Урон
    val distanceThenReduceDamageStart_meters: Float, // F | Снижение урона с, м:
    val distanceThenReducedDamageIsMinumal_meters: Float, // G | Минимальный урон с, м:
) {
    val damageReducePerMeter: Float
        get() =
            (baseDamage - minDamage) / (distanceThenReducedDamageIsMinumal_meters - distanceThenReduceDamageStart_meters)
    var distance_meters: Float = 0F // userInput
    val reducedDamage: Float
        get() {
            val calculatedDamage: Float =
                baseDamage - (distance_meters - distanceThenReduceDamageStart_meters) * damageReducePerMeter
            return Math.max(minDamage, Math.min(baseDamage, calculatedDamage))
//        return minDamage.coerceAtLeast(maxDamage.coerceAtMost(calculatedDamage))
            //TODO - test it
        }

    constructor(
        gunName: String,
        bulletSpeedFromGun: Int,
        gravityCoefficient: Int,
        baseDamage: Int,
        minDamage: Int,
        distanceThenReduceDamageStart_meters: Int,
        distanceThenReducedDamageIsMinumal_meters: Int,
    ) : this(
        gunName,
        bulletSpeedFromGun.toFloat(),
        gravityCoefficient.toFloat(),
        baseDamage.toFloat(),
        minDamage.toFloat(),
        distanceThenReduceDamageStart_meters.toFloat(),
        distanceThenReducedDamageIsMinumal_meters.toFloat()
    )
}