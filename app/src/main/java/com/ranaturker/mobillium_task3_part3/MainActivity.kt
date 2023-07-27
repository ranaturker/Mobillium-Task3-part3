package com.ranaturker.mobillium_task3_part3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.ranaturker.mobillium_task3_part3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // ActivityMainBinding adında bir değişken tanımlıyoruz
    // Bu, layout dosyamızdaki bileşenlere erişim sağlamamıza yardımcı oluyor
    private lateinit var binding: ActivityMainBinding

    // onCreate() metodu, bir Activity oluşturulduğunda çağrılıyor
    override fun onCreate(savedInstanceState: Bundle?) {
        // Üst sınıfın onCreate() metodunu çağırarak temel işlemleri gerçekleştiriyoruz
        super.onCreate(savedInstanceState)

        // ActivityMain layout dosyası inflate edilerek içeriklerine erişim sağlanıyor
        binding = ActivityMainBinding.inflate(layoutInflater)

        // Belirtilen layout dosyası Activity'nin arayüzü olarak ayarlanıyor
        setContentView(binding.root)
    }

    // onSupportNavigateUp() metodu, ActionBar'daki geri(up) butonuna basıldığında çağrılır
    override fun onSupportNavigateUp(): Boolean {
        // findNavController() ile, NavigationHostFragment içindeki NavController örneğine erişiriz
        // R.id.nav_host_fragment, NavController'ın bulunacağı fragmentın kimliğini temsil eder
        val navController = findNavController(R.id.nav_host_fragment)

        // Eğer Navigation Component ile bir önceki ekrana geçiş yapılıyorsa bunu gerçekleştirir
        // Değilse Activity'nin varsayılan geri dönüş fonksiyonunu devralır
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}

