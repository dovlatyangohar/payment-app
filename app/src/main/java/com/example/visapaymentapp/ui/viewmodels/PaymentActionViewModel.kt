package com.example.visapaymentapp.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.visapaymentapp.model.CardInfo
import com.example.visapaymentapp.repository.CardInfoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PaymentActionViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CardInfoRepository = CardInfoRepository(application)

    fun insertDataToDB(data: CardInfo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(data)
        }
    }

    fun deleteData(data: CardInfo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteData(data)
        }
    }

}