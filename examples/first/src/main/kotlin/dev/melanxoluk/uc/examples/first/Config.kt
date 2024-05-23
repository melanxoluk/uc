package dev.melanxoluk.uc.examples.first

import dev.melanxoluk.uc.configuration.ConfigPath
import dev.melanxoluk.uc.configuration.UConfig
import dev.melanxoluk.uc.configuration.UConfigReference
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime

data class Config(
    val text: String,
    val textArr: List<String>,
    val integer: Int,
    val integerArr: List<Int>,
    val real: Double,
    val realArr: List<Double>,
    val decimal: BigDecimal,
    val decimalArr: List<BigDecimal>,
    val date: LocalDate,
    val dateArr: List<LocalDate>,
    val dateTime: LocalDateTime,
    val dateTimeArr: List<LocalDateTime>,
    val timestamp: Instant,
    val timestampArr: List<Instant>,
    val nestedObject: Config?,
    val nestedObjectArr: List<Config>,
    val otherConfig: UConfigReference,
    val otherConfigArr: List<UConfigReference>,
    val file: UConfigReference,
    val fileArr: List<UConfigReference>
) : UConfig
