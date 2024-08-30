package io.github.bf2_mod_calculator.models

import java.math.BigDecimal

typealias Calibre = String
typealias Ammo = String

class GunTableContent(
    val cartridgesForCalibre: Map<Calibre, List<Ammo>>,
    val tableLinesForAmmo: Map<Ammo, List<GunTableContentLine>>
)

class GunTableContentLine(
    val gunName: String,
    val bulletSpeedFromGun: BigDecimal = BigDecimal.ZERO,
    val gravityCoefficient: BigDecimal,
    val baseDamage: BigDecimal, // D |  Урон
    val minDamage: BigDecimal, // E |  Мин.Урон
    val distanceThenReduceDamageStart_meters: BigDecimal, // F | Снижение урона с, м:
    val distanceThenReducedDamageIsMinumal_meters: BigDecimal, // G | Минимальный урон с, м:
) {
    val damageReducePerMeter: BigDecimal
        get() =
            (baseDamage - minDamage) / (distanceThenReducedDamageIsMinumal_meters - distanceThenReduceDamageStart_meters)

    // TODO : выпил когда будет норм парсес
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
        bulletSpeedFromGun.toBigDecimal(),
        gravityCoefficient.toBigDecimal(),
        baseDamage.toBigDecimal(),
        minDamage.toBigDecimal(),
        distanceThenReduceDamageStart_meters.toBigDecimal(),
        distanceThenReducedDamageIsMinumal_meters.toBigDecimal()
    )

    // TODO : выпил когда будет норм парсес
    constructor(
        gunName: String,
        bulletSpeedFromGun: Double,
        gravityCoefficient: Double,
        baseDamage: Double,
        minDamage: Double,
        distanceThenReduceDamageStart_meters: Double,
        distanceThenReducedDamageIsMinumal_meters: Double,
    ) : this(
        gunName,
        bulletSpeedFromGun.toBigDecimal(),
        gravityCoefficient.toBigDecimal(),
        baseDamage.toBigDecimal(),
        minDamage.toBigDecimal(),
        distanceThenReduceDamageStart_meters.toBigDecimal(),
        distanceThenReducedDamageIsMinumal_meters.toBigDecimal()
    )
}