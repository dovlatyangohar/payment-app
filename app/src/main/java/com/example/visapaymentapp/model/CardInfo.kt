package com.example.visapaymentapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "card_info")
data class CardInfo(
    @PrimaryKey
    val cardNumber: String,
    val expireDate: String,
    val cvv: String
) : Parcelable
