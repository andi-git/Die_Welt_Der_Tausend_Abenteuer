package eu.ahammer.dieweltdertausendabenteuer.viewmodel.item

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import eu.ahammer.dieweltdertausendabenteuer.model.DWdtADatabase
import eu.ahammer.dieweltdertausendabenteuer.model.Item

class ItemViewModel(application: Application) : AndroidViewModel(application) {

    val repository: ItemRepository = ItemRepository(DWdtADatabase.get(application).itemDao())

    fun addContacts(item: Item) {
        repository.insert(item)
    }

    fun getAllContacts(): LiveData<List<Item>> = repository.getAll()
}