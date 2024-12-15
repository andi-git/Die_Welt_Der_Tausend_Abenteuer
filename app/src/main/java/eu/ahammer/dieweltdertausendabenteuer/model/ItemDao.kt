package eu.ahammer.dieweltdertausendabenteuer.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ItemDao {

    @Query("SELECT * FROM item ORDER BY item.text COLLATE NOCASE ASC")
    fun loadAllForLiveData(): LiveData<List<Item>>

    @Query("SELECT * FROM item ORDER BY item.text COLLATE NOCASE ASC")
    fun loadAll(): List<Item>

    @Query("SELECT * FROM item WHERE id = :id")
    fun loadById(id: Int): Item

    @Insert
    fun insert(item: Item)

    @Update
    fun update(item: Item)

    @Delete
    fun delete(item: Item)
}