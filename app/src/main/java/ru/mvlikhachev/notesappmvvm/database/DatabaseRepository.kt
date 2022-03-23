package ru.mvlikhachev.notesappmvvm.database

import androidx.lifecycle.LiveData
import ru.mvlikhachev.notesappmvvm.model.Note

interface DatabaseRepository {

    val readAll: LiveData<List<Note>>

    // CRUD
    suspend fun create(note: Note, onSuccess:()-> Unit)

    suspend fun update(note: Note, onSuccess:()-> Unit)

    suspend fun delete(note: Note, onSuccess:()-> Unit)
}