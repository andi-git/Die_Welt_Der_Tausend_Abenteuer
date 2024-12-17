package eu.ahammer.dieweltdertausendabenteuer.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import eu.ahammer.dieweltdertausendabenteuer.util.NothingToDoHere

@Database(entities = [Item::class, Hint::class], version = 5)
abstract class DWdtADatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

    abstract fun hintDao(): HintDao

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
                    .addMigrations(MIGRATION_1_2)
                    .addMigrations(MIGRATION_2_3)
                    .addMigrations(MIGRATION_3_4)
                    .addMigrations(MIGRATION_4_5)
                    .build()

                INSTANCE = roomDatabaseInstance
                return roomDatabaseInstance
            }
        }
    }
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        NothingToDoHere("no migration needed")
    }
}

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        NothingToDoHere("no migration needed")
    }
}

val MIGRATION_3_4 = object : Migration(3, 4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        NothingToDoHere("no migration needed")
    }
}

val MIGRATION_4_5 = object : Migration(4, 5) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE IF NOT EXISTS`Hint` (`id` INTEGER NULL, `text` TEXT, PRIMARY KEY(`id`))");
    }
}
