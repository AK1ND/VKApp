package com.example.vkapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class TokenData : ViewModel() {
    val token = MutableLiveData<String>()


}