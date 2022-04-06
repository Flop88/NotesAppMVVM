package ru.mvlikhachev.notesappmvvm.database.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import ru.mvlikhachev.notesappmvvm.database.DatabaseRepository
import ru.mvlikhachev.notesappmvvm.model.Note
import ru.mvlikhachev.notesappmvvm.utils.Constants
import ru.mvlikhachev.notesappmvvm.utils.FIREBASE_ID
import ru.mvlikhachev.notesappmvvm.utils.LOGIN
import ru.mvlikhachev.notesappmvvm.utils.PASSWORD

class AppFirebaseRepository : DatabaseRepository {

    private val mAuth = FirebaseAuth.getInstance()
    private val  database = Firebase.database.reference
        .child(mAuth.currentUser?.uid.toString())

    override val readAll: LiveData<List<Note>> = AllNotesLiveData()

    override suspend fun create(note: Note, onSuccess: () -> Unit) {
        val noteId = database.push().key.toString()
        val mapNotes = hashMapOf<String, Any>()

        mapNotes[FIREBASE_ID] = noteId
        mapNotes[Constants.Keys.TITLE] = note.title
        mapNotes[Constants.Keys.SUBTITLE] = note.subtitle

        database.child(noteId)
            .updateChildren(mapNotes)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { Log.d("checkData", "Failed to add new note") }
    }

    override suspend fun update(note: Note, onSuccess: () -> Unit) {
        val noteId = database.push().key.toString()
        val mapNote = hashMapOf<String, Any>()

        mapNote[FIREBASE_ID] = noteId
        mapNote[Constants.Keys.TITLE] = note.title
        mapNote[Constants.Keys.SUBTITLE] = note.subtitle

        database.child(noteId)
            .updateChildren(mapNote)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { Log.d("checkData", "Failed to update new note") }
    }

    override suspend fun delete(note: Note, onSuccess: () -> Unit) {
        database.child(note.firebaseId).removeValue()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { Log.d("checkData", "Failed to delete note") }
    }

    override fun signOut() {
        mAuth.signOut()
    }

    override fun connectToDatabase(onSuccess: () -> Unit, onFail: (String) -> Unit) {
       mAuth.signInWithEmailAndPassword(LOGIN, PASSWORD)
           .addOnSuccessListener { onSuccess() }
           .addOnFailureListener {
               mAuth.createUserWithEmailAndPassword(LOGIN, PASSWORD)
                   .addOnSuccessListener { onSuccess() }
                   .addOnFailureListener { onFail(it.message.toString()) }
           }
    }
}