package eu.ahammer.dieweltdertausendabenteuer.view.fight

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FightViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Zeit zum Kämpfen! 🦷 ${(0..10).random()}"
    }
    val text: LiveData<String> = _text
}