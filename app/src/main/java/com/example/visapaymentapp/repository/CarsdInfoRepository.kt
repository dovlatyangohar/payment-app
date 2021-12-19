package com.example.visapaymentapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.visapaymentapp.db.CardInfoDao
import com.example.visapaymentapp.db.CardInfoDataBase
import com.example.visapaymentapp.model.CardInfo

class CardInfoRepository(context: Context) {
    private val cardInfoDao: CardInfoDao

    fun cardInfoList():List<CardInfo>{
        return cardInfoDao.getAllResultsFromDb()
    }

    fun insertData(cardInfo: CardInfo) {
        cardInfoDao.insertCardInfo(cardInfo)
    }

    fun deleteData(cardInfo: CardInfo) {
        cardInfoDao.delete(cardInfo)
    }

    init {
        val db = CardInfoDataBase.getDatabaseInstance(context)
        cardInfoDao = db.getCardInfoDao()
    }

}
