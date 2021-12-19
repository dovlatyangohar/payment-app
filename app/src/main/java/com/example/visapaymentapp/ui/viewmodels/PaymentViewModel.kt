package com.example.visapaymentapp.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.visapaymentapp.model.CardInfo
import com.example.visapaymentapp.repository.CardInfoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PaymentViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CardInfoRepository = CardInfoRepository(application)
     val mCardInfoList by lazy { MutableLiveData<List<CardInfo>>() }

      fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            mCardInfoList.postValue(repository.cardInfoList())
        }

    }
}

//adapter viewTypes
// mvvm
// koin
// navigationUI