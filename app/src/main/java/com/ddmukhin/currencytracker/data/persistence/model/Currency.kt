package com.ddmukhin.currencytracker.data.persistence.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Currency(
    @PrimaryKey val base: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "value") val value: Double
)