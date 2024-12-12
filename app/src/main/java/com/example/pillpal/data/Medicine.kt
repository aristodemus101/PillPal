package com.example.pillpal.data

import java.time.LocalDate

data class Medicine(
    val name: String,
    val dosage: String,
    val type: String? = null,
    val date: LocalDate? = null,
    val timeOfDay: String? = null,
    val firstStarted: LocalDate? = null,
    val doctor: String? = null,
    val taken: Boolean = false
) {
    init {
        require(name.isNotBlank()) { "Medicine name cannot be blank" }
        require(dosage.isNotBlank()) { "Dosage cannot be blank" }
    }
}