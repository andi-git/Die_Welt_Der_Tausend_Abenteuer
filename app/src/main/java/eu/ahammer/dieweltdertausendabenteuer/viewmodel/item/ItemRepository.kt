package eu.ahammer.dieweltdertausendabenteuer.viewmodel.item

import androidx.lifecycle.LiveData
import eu.ahammer.dieweltdertausendabenteuer.model.Item
import eu.ahammer.dieweltdertausendabenteuer.model.ItemDao

class ItemRepository(val dao : ItemDao) {

    fun getAll() : LiveData<List<Item>>{
        return dao.loadAll()
    }

    fun insert(item: Item) {dao.insert(item)}

    fun delete(item: Item) {
        dao.delete(item)
    }
}