package io.github.bf2_mod_calculator.models

class CsvRow(
    val calibre: Calibre,
    val ammo: Ammo,
    val gunName: String,

    val bulletSpeedFromGun: String,
    val gravityCoefficient: String,
    val baseDamage: String, // D |  Урон
    val minDamage: String, // E |  Мин.Урон
    val distanceThenReduceDamageStart_meters: String, // F | Снижение урона с, м:
    val distanceThenReducedDamageIsMinumal_meters: String, // G | Минимальный урон с, м:
)
