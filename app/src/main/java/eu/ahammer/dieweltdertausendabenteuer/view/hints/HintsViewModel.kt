package eu.ahammer.dieweltdertausendabenteuer.view.hints

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HintsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Die Hinweise"
    }
    val text: LiveData<String> = _text
}