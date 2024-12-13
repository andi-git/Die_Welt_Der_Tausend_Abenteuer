package eu.ahammer.dieweltdertausendabenteuer.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 4)
abstract class DWdtADatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        var INSTANCE: DWdtADatabase? = null

        fun get(context: Context): DWdtADatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val roomDatabaseInstance = Room.databaseBuilder(
                    context,
                    DWdtADatabase::class.java,
                    "die_welt_der_tausend_abenteuer-database"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    //.addMigrations(MIGRATION_1_2)
                    .build()

                INSTANCE = roomDatabaseInstance
                return roomDatabaseInstance
            }
        }
    }
}

//val MIGRATION_1_2 = object : Migration(1, 2) {
//    override fun migrate(database: SupportSQLiteDatabase) {
//        NothingToDoHere("no migration needed")
//    }
//}
