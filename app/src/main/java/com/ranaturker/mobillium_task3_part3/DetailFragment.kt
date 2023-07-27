package com.ranaturker.mobillium_task3_part3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.ranaturker.mobillium_task3_part3.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    // ViewModel örneği oluşturularak diğer fragmentlarla veri paylaşımı sağlanıyor
    private val sharedViewModel: GameSharedViewModel by activityViewModels()

    // Fragment ile bağlama sınıfı arasında veri iletişimi için kullanılacak değişken
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Fragment'ın kullanıcı arayüzü oluşturuluyor
        binding = FragmentDetailBinding.inflate(layoutInflater)
        // Gizli sayıyı takip eden metodu çağırıyoruz
        observeHiddenNumber()
        return binding.root
    }

    private fun observeHiddenNumber() {
        // Gizli sayıyı izlemek için LiveData'ya gözlemci ekleniyor
        sharedViewModel.hiddenNumber.observe(viewLifecycleOwner) { hiddenNumber ->
            // Gizli sayı değiştiğinde, TextView öğesine yeni değeri atayarak gösteriyoruz
            binding.hiddenNumberTextView.text = hiddenNumber
        }
    }
}

