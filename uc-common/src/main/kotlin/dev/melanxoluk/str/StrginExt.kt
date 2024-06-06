package dev.melanxoluk.str

import java.util.*
import kotlin.random.Random


val String.snake get() = snakeTransform(words)
val String.snakel get() = snakeTransform(words).lowercase(Locale.getDefault())
val String.pascal get() = camelTransform(words)
val String.camel get() = camelTransform(words).replaceFirstChar { it.lowercase(Locale.getDefault()) }
val String.cebab get() = cebabTransform(words)
val String.cebabl get() = cebabTransform(words).lowercase()

private val String.words: List<Pair<Int, Int>>
    get() {
        val words = mutableListOf<Pair<Int, Int>>()

        var wordBegun = false
        var wordStart = 0
        var wordWholeUpper = false
        var wordLettersAmount = 0

        for ((index, ch) in this.withIndex()) {
            // if last letter was an end of word,
            // then new letter is start of new word
            if (!wordBegun) {
                wordBegun = true
                wordStart = index
                wordWholeUpper = ch.isUpperCase()
                wordLettersAmount = 1

                // words always divided by bottom line
                // but sometimes the next letter could be divider too: SOME__HERE
            } else if (ch == '_' || ch == '-' || ch == ' ') {
                wordBegun = false
                wordWholeUpper = false
                wordLettersAmount = 0
                words.add(wordStart to index)

                // determine finish of word
            } else {
                val isUpper = ch.isUpperCase()
                val isLower = ch.isLowerCase()

                // "BcdAbc" - 'A'
                if (isUpper && !wordWholeUpper) {
                    words.add(wordStart to index)
                    wordBegun = true
                    wordStart = index
                    wordWholeUpper = true
                    wordLettersAmount = 1

                    // "ABC"
                } else if (isUpper && wordWholeUpper) {
                    wordLettersAmount++

                    // "ABilling" - 'i'
                } else if (isLower && wordWholeUpper && wordLettersAmount > 1) {
                    words.add(wordStart to index - 1)

                    wordBegun = true
                    wordStart = index - 1
                    wordLettersAmount = 2
                    wordWholeUpper = false

                    // "Abc" - 'b'
                } else if (isLower) {
                    wordLettersAmount++
                    wordWholeUpper = false
                }
            }
        }

        // add last word
        if (wordLettersAmount != 0) {
            words.add(wordStart to length)
        }

        return words
    }

/**
 * Transforms string to snake case presentation. Divide words by first capital letter
 * or take continuous sequence of upper letters.
 *
 * Examples:
 * - ADMBillingEXAMPLE - ADM_BILLING_EXAMPLE
 * - AdmBillingExample - ADM_BILLING_EXAMPLE
 * - ADM_BILLING_EXAMPLE - ADM_BILLING_EXAMPLE
 * - ADMBILLINGEXAMPLE - ADMBILLINGEXAMPLE
 * - ADm_BILLIng_EXample - A_DM_BILL_ING_E_XAMPLE
 * - aDm_bILLIng_exAmple - A_DM_B_ILL_ING_EX_AMPLE
 */
private fun String.snakeTransform(words: List<Pair<Int, Int>>): String = words.joinToString("_") {
    substring(it.first, it.second).uppercase(Locale.getDefault())
}

/**
 * Transforms string to camel case presentation. Divide words by first capital letter
 * or take continuous sequence of upper letters.
 *
 * Examples:
 * - ADMBillingEXAMPLE - AdmBillingExample
 * - AdmBillingExample - AdmBillingExample
 * - ADM_BILLING_EXAMPLE - AdmBillingExample
 * - ADMBILLINGEXAMPLE - Admbillingexample
 * - ADm_BILLIng_EXample - ADmBillIngEXample
 * - aDm_bILLIng_exAmple - ADmBIllIngExAmple
 */
private fun String.camelTransform(words: List<Pair<Int, Int>>): String = words.joinToString("") {
    substring(it.first, it.second).lowercase(Locale.getDefault())
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}

private fun String.cebabTransform(words: List<Pair<Int, Int>>): String =
    words.map { substring(it.first, it.second).lowercase() }
        // allows to filter 'empty' (-,__) words with several continued underscores, like FROM__CD
        .filter { it.all { ch -> ch == '_' || ch == '-' }.not() }
        .joinToString("-")
