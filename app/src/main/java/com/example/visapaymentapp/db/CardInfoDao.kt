package com.example.visapaymentapp.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.visapaymentapp.model.CardInfo

@Dao
interface CardInfoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCardInfo(cardInfo: CardInfo)

    @Delete
    fun delete(cardInfo: CardInfo)

    @Query("SELECT * FROM card_info")
    fun getAllResultsFromDb(): List<CardInfo>

}