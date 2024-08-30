package io.github.bf2_mod_calculator.compose

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import java.math.BigDecimal
import java.util.regex.Pattern

@Composable fun OnlyNumberTextField(
    value: Number = 100,
    modifier: Modifier = Modifier,
    onValueUpdated: (BigDecimal) -> Unit
) {

    var textState by remember { mutableStateOf("" + value) }
    val focusManager = LocalFocusManager.current

    TextField(
        value = textState,
        onValueChange = {
            if (it.isEmpty() || it.isNumber()) {
                textState = it
                onValueUpdated(textState.toBigDecimal())
            }
            println("input = '$it'")
            println("state = '$textState'")
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions {
            focusManager.clearFocus()
        },
        modifier = modifier
    )
}


/**
 * Расшифровка regex ^[0-9]+([.,][0-9]+)?\z
 * ```
 * ^       - начало строки
 * [0-9]+  - любой символ из диапазона 0-9 (совпадения от ОДИН до БЕСКОНЕЧНОСТИ раз)
 * (       - начало группы
 * [.,]    - символ ТОЧКА или ЗАПЯТАЯ (ОДИН раз)
 * [0-9]+  - любой символ из диапазона 0-9 (совпадения от ОДИН до БЕСКОНЕЧНОСТИ раз)
 * )       - конец группы
 * ?       - группа (совпадения НОЛЬ или ОДИН раз)
 * \\z     - конец строки (специально что бы избежать '\n', а то тык-тык на Enter и эта хрень появляется, а нам она не нужна)
 * ```
 *
 * ```
 * regex.matches("100")     // true
 * regex.matches("100.50")  // true
 * regex.matches("100,")    // false
 * regex.matches("100.\n")  // false
 * regex.matches("100\n")   // false
 * ```
 *
 * инструмент для работы с regexp: [regex101.com](https://regex101.com/)
 */
private val decimalNumberRegex = Pattern.compile("^[0-9]+([.,][0-9]+)?\\z")

private fun String.isNumber(): Boolean = decimalNumberRegex.matcher(this).find()
