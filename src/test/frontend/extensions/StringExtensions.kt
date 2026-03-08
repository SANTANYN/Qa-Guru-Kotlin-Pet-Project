package frontend.extensions

/**
 * Парсит строку с ценой (например "$2.5") в Float.
 * Убирает все символы кроме цифр и точки.
 */
fun String.toPrice(): Float =
    this.filter { it.isDigit() || it == '.' }.toFloat()
