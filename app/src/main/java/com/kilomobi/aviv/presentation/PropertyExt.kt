package com.kilomobi.aviv.presentation

import com.kilomobi.aviv.domain.Property

fun Property.generateDescription(): String {
    val descriptionBuilder = StringBuilder()

    descriptionBuilder.append("Ce bien immobilier ")

    this.propertyType?.let {
        descriptionBuilder.append("de type $it ")
    }

    if (this.rooms != null) {
        descriptionBuilder.append("comportant ${this.rooms} pièces ")
    }

    if (this.bedrooms != null) {
        descriptionBuilder.append("dont ${this.bedrooms} chambres ")
    }

    descriptionBuilder.append("d'une superficie totale de ${this.area.formatArea("hectares")} ")

    this.city.let {
        descriptionBuilder.append("est idéalement situé à $it. ")
    }

    if (this.price != null) {
        descriptionBuilder.append("Son prix est de ${this.price.formatPrice("€")} net vendeur. ")
    }

    this.professional?.let {
        descriptionBuilder.append("Proposé par $it. ")
    }

    return descriptionBuilder.toString().trim()
}