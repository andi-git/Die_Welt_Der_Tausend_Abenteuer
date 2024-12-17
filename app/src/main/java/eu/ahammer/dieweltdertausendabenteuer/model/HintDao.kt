package eu.ahammer.dieweltdertausendabenteuer.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface HintDao {

    @Query("SELECT * FROM hint ORDER BY hint.text COLLATE NOCASE ASC")
    fun loadAllForLiveData(): LiveData<List<Hint>>

    @Query("SELECT * FROM hint ORDER BY hint.text COLLATE NOCASE ASC")
    fun loadAll(): List<Hint>

    @Query("SELECT * FROM hint WHERE id = :id")
    fun loadById(id: Int): Hint

    @Insert
    fun insert(hint: Hint)

    @Update
    fun update(hint: Hint)

    @Delete
    fun delete(hint: Hint)
}