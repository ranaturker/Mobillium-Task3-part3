package com.ranaturker.mobillium_task3_part3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ranaturker.mobillium_task3_part3.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    // Görünüm öğeleri ve görünüm modelleri
    private lateinit var numberButtons: List<Button> // Buton öğelerinin bir listesi
    private val viewModel: GameViewModel by viewModels() // Bu Fragment için ViewModel

    // Aktivite ile paylaşılan ViewModel
    private val sharedViewModel: GameSharedViewModel by activityViewModels()
    private lateinit var binding: FragmentGameBinding // View binding için nesne

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(layoutInflater)
        bindUI()
        viewModel.generateRandomValues()
        return binding.root
    }

    private fun bindUI() = with(binding) {
        numberButtons = listOf(
            button0,
            button1,
            button2,
            button3,
            button4,
            button5,
            button6,
            button7,
            button8,
            button9
        )
        // "Guess" butonu için click listener
        guessButton.setOnClickListener {
            val guess = guessEditText.text.toString().toInt()
            checkGuess(guess) // Tahmini doğrulamak için checkGuess fonksiyonu çağrılıyor
        }
        clearButton.setOnClickListener {
            guessEditText.text.clear() // EditText'teki yazıyı siler
        }

        // "Rastgele Karakter" metin görünümü için clickListener
        randomCharTextView.setOnClickListener {
            val result = viewModel.randomNumber.value?.toString() ?: "Değer Bulunamadı"
            // ViewModel'dan rastgele sayıyı alarak randomCharTextView'de gösteriyor
            randomCharTextView.text = result
            // Diğer bir Fragment'ta kullanılmak üzere sharedViewModel'daki gizli sayıyı ayarlar
            sharedViewModel.setHiddenNumber(result)
            // NavController kullanarak başka bir Fragment'a (DetailFragment) geçiyor
            findNavController().navigate(GameFragmentDirections.actionGameFragmentToDetailFragment())
        }

        // numberButtons için clickListener
        for (button in numberButtons) {
            button.setOnClickListener {
                val buttonText = button.text.toString() // Tıklanan butondan metni (sayıyı) alır
                val currentGuess =
                    guessEditText.text.toString()
                // Tıklanan sayıyı TahminEditText'e ekliyor
                guessEditText.setText("$currentGuess$buttonText")
            }
        }

        // ViewModel'daki randomChar LiveData'sı gözlemlenir
        // randomCharTextView'i buna göre günceller
        viewModel.randomChar.observe(viewLifecycleOwner) { randomChar ->
            randomCharTextView.text = randomChar.toString()
        }
    }

    // Kullanıcının tahminini oluşturulan rastgele değerle karşılaştırmak için bir fonksiyon
    private fun checkGuess(guess: Int) {
        val isMatch =
            // Tahminin doğru olup olmadığını kontrol etmek için ViewModel fonksiyonunu çağırır
            viewModel.checkGuess(guess)
        // Tahminin doğruluğuna göre resultTextView'i buna göre günceller,
        if (isMatch) {
            binding.resultTextView.text = "Kazandınız!"
        } else {
            binding.resultTextView.text = "Tekrar Deneyin."
        }
    }
}
