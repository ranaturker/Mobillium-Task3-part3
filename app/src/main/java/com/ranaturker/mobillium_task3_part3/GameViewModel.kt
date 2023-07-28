package com.ranaturker.mobillium_task3_part3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    // _randomNumber adında MutableLiveData türünde bir değişken oluşturuluyor
    private val _randomNumber = MutableLiveData<Int>()

    // randomNumber adında oluşturulan LiveData türündeki değişken erişime açık hale getiriliyor
    val randomNumber: LiveData<Int> get() = _randomNumber

    // _randomChar adında MutableLiveData türünde bir değişken oluşturuluyor.
    private val _randomChar = MutableLiveData<Char>()

    // randomChar adında oluşturulan LiveData türündeki değişken erişime açık hale getiriliyor
    val randomChar: LiveData<Char> get() = _randomChar

    var canGenerateNumber : Boolean = true

    // Bu fonksiyon, rastgele bir sayı ve karakter üretir ve ilgili değişkenlere atar
    fun generateRandomValues() {
        // _randomNumber, 1 ile 10 arasında rastgele bir sayıyla güncellenir
        _randomNumber.value = (1..10).random()

        // _randomChar, 'A' ile 'Z' arasında rastgele bir karakterle güncellenir
        _randomChar.value = ('A'..'Z').random()
    }

    // checkGuess fonksiyonu, kullanıcının tahminini kontrol eder ve sonucu döndürür
    fun checkGuess(guess: Int): Boolean {
        // guess, _randomNumber değeriyle karşılaştırılır ve sonuç döndürülür
        return guess == _randomNumber.value
    }
}

