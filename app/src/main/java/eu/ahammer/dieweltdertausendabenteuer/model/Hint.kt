package eu.ahammer.dieweltdertausendabenteuer.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Hint(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "text") var text: String
)