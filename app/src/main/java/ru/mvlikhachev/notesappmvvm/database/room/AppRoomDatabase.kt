package ru.mvlikhachev.notesappmvvm.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.mvlikhachev.notesappmvvm.database.room.dao.NoteRoomDao
import ru.mvlikhachev.notesappmvvm.model.Note

@Database(entities = [Note::class], version = 1)
abstract class AppRoomDatabase: RoomDatabase() {

    abstract fun getRoomDao(): NoteRoomDao

    companion object {

        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getInstance(context: Context): AppRoomDatabase {
            return if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppRoomDatabase::class.java,
                    "notes_database"
                ).build()
                INSTANCE as AppRoomDatabase
            } else INSTANCE as AppRoomDatabase
        }
    }

}