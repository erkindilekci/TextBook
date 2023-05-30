package com.erkindilekci.textbook.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.erkindilekci.textbook.domain.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao

    companion object {
        const val DATABASE_NAME = "note_database"
    }
}
