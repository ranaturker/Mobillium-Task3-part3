package com.ranaturker.mobillium_task3_part3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// ViewModel sınıfı, UI ile verileri paylaşmak ve yönetmek için kullanılır
class GameSharedViewModel : ViewModel() {
    // MutableLiveData, değiştirilebilir veri türündedir ve LiveData'yı sarmalar
    // _hiddenNumber, gizli sayıyı içeren değiştirilebilir bir veri tutucusudur
    private val _hiddenNumber = MutableLiveData<String>()

    // hiddenNumber, gizli sayıyı takip etmek için dışarı açık olan LiveData'dır
    // Dışarıdan erişilebilir ancak değiştirilemez, yani sadece okunabilir
    val hiddenNumber: LiveData<String> get() = _hiddenNumber

    // setHiddenNumber, gizli sayıyı güncellemek için kullanılan bir işlevdir
    // Dışarıdan bir sayı alır ve _hiddenNumber'ı günceller, böylece UI yeni değeri alır
    fun setHiddenNumber(number: String) {
        _hiddenNumber.value = number
    }
}
