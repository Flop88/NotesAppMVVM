package ru.mvlikhachev.notesappmvvm

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import ru.mvlikhachev.notesappmvvm.model.Note
import ru.mvlikhachev.notesappmvvm.utils.TYPE_FIREBASE
import ru.mvlikhachev.notesappmvvm.utils.TYPE_ROOM
import java.lang.IllegalArgumentException

class MainViewModel (application: Application) : AndroidViewModel(application) {

    val readTest: MutableLiveData<List<Note>> by lazy {
        MutableLiveData<List<Note>>()
    }
    val dbType: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    init {
        readTest.value =
            when(dbType.value) {
                TYPE_ROOM -> listOf<Note>(
                    Note(title = "Note 1", subtitle = "Subtitle for note 1"),
                    Note(title = "Note 2", subtitle = "Subtitle for note 2"),
                    Note(title = "Note 3", subtitle = "Subtitle for note 3"),
                    Note(title = "Note 4", subtitle = "Subtitle for note 4"),
                    Note(title = "Note 5", subtitle = "Subtitle for note 5"),
                )
                TYPE_FIREBASE -> listOf()
                else -> listOf()
            }
    }

    fun initDatabase(type: String) {
        dbType.value = type
        Log.d("checkData", "MainViewModel initDatabase with type: $type")
    }
}

class MainViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application = application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}