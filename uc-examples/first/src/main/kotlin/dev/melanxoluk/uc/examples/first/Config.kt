package dev.melanxoluk.uc.examples.first

import dev.melanxoluk.uc.configuration.Config
import dev.melanxoluk.uc.configuration.ConfigReference
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
    val otherConfig: ConfigReference,
    val otherConfigArr: List<ConfigReference>,
    val file: ConfigReference,
    val fileArr: List<ConfigReference>
) : Config
