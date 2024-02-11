package com.kilomobi.aviv.presentation

fun Double.formatArea(unit: String): String {
    val formattedArea = if (this % 1 == 0.0) {
        String.format("%.0f", this)
    } else {
        this.toString()
    }

    return "$formattedArea $unit"
}

fun Double.formatPrice(symbol: String): String {
    val priceString = this.toLong().toString()
    val formattedPrice = StringBuilder()

    var count = 0
    for (i in priceString.length - 1 downTo 0) {
        formattedPrice.append(priceString[i])
        count++
        if (count == 3 && i > 0) {
            formattedPrice.append(' ')
            count = 0
        }
    }

    return formattedPrice.reverse().toString() + " $symbol"
}