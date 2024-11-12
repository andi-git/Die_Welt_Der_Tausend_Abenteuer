package eu.ahammer.dieweltdertausendabenteuer.ui.items

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Deine Gegenstände"
    }
    val text: LiveData<String> = _text
}