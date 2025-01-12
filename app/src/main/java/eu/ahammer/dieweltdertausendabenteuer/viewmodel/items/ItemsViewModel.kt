package eu.ahammer.dieweltdertausendabenteuer.viewmodel.items

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import eu.ahammer.dieweltdertausendabenteuer.model.DWdtADatabase
import eu.ahammer.dieweltdertausendabenteuer.model.Item
import eu.ahammer.dieweltdertausendabenteuer.model.ItemDao

class ItemsViewModel(application: Application) : AndroidViewModel(application) {

    val itemDao: ItemDao = DWdtADatabase.get(getApplication()).itemDao()

    fun addItem(item: Item) {
        itemDao.insert(item)
    }

    fun updateItem(item: Item) {
        itemDao.update(item)
    }

    fun deleteItem(item: Item) {
        itemDao.delete(item)
    }

    fun getItems(): LiveData<List<Item>> = itemDao.loadAllForLiveData()
}