package eu.ahammer.dieweltdertausendabenteuer.ui.fight

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class FightViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Zeit zum Kämpfen! 🦷 ${(0..10).random()}"
    }
    val text: LiveData<String> = _text
}