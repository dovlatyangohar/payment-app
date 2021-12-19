package com.example.visapaymentapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.visapaymentapp.model.CardInfo

@Database(entities = [CardInfo::class], version = 2, exportSchema = false)
abstract class CardInfoDataBase : RoomDatabase() {
    abstract fun getCardInfoDao(): CardInfoDao

    companion object {
        @Volatile
        private var INSTANCE: CardInfoDataBase? = null

        fun getDatabaseInstance(context: Context): CardInfoDataBase {
            if (INSTANCE == null) {
                synchronized(CardInfoDataBase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            CardInfoDataBase::class.java, "cardInfo_database"
                        ).fallbackToDestructiveMigration().build()
                    }
                }
            }
            return INSTANCE!!
        }
    }

}