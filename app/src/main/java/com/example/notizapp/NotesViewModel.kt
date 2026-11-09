package com.example.notizapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// ============================================
// KOTLIN: ViewModel mit LiveData
// ============================================
// ViewModel überlebt Configuration Changes (z.B. Screen Rotation)
// LiveData informiert die UI automatisch über Änderungen
//
// Wichtige Konzepte:
// - ViewModel: Hält Daten und Logik, unabhängig vom Activity Lifecycle
// - MutableLiveData: Kann verändert werden (postValue, setValue)
// - LiveData: Nur lesbar für die UI
// - Companion Object: Ähnlich wie static in Java
// ============================================

/**
 * ViewModel für die Verwaltung von Notizen
 * Speichert Notizen in SharedPreferences (einfache Persistenz)
 */
class NotesViewModel : ViewModel() {
    
    // ============================================
    // KOTLIN: Private Properties mit Backing Field
    // ============================================
    // _notes ist die interne MutableLiveData
    // notes ist die öffentliche LiveData (nur lesbar)
    // Dies ist ein häufiges Pattern in Android
    // ============================================
    
    private val _notes = MutableLiveData<MutableList<Note>>().apply {
        value = mutableListOf()
    }
    
    // Öffentliche LiveData - UI kann nur lesen, nicht ändern
    val notes: LiveData<MutableList<Note>> = _notes
    
    // SharedPreferences für Persistenz
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var gson: Gson
    private val PREFS_NAME = "NotesPrefs"
    private val NOTES_KEY = "notes_list"
    
    // ============================================
    // KOTLIN: Companion Object (wie static in Java)
    // ============================================
    // Companion Objects sind Singleton-Objekte innerhalb einer Klasse
    // Ähnlich wie static Methoden/Properties in Java
    // ============================================
    
    companion object {
        // Erzeugt eine neue ID (einfach hochzählend)
        fun generateId(): Long {
            return System.currentTimeMillis() // Einfache Unique-ID
        }
    }
    
    // ============================================
    // KOTLIN: Init-Block
    // ============================================
    // Wird beim Erstellen der Klasse ausgeführt
    // Ähnlich wie Konstruktoren in Java
    // ============================================
    
    init {
        gson = Gson()
    }
    
    // ============================================
    // Initialisierung mit Context (wird von outside aufgerufen)
    // ============================================
    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        loadNotes()
    }
    
    // ============================================
    // KOTLIN: Funktionen (Methods)
    // ============================================
    // In Kotlin: fun functionName(param: Type): ReturnType
    // In Java:   public ReturnType functionName(Type param)
    //
    // Wichtige Unterschiede:
    // - Kein 'new' für Objekte: Note(id, title, content)
    // - Keine Semikolons nötig
    // - Letzte Expression in einer Funktion ist automatisch der Return-Wert
    // ============================================
    
    /**
     * Fügt eine neue Notiz hinzu
     */
    fun addNote(title: String, content: String) {
        // ============================================
        // KOTLIN: Listen-Operationen
        // ============================================
        // .add() - wie in Java
        // .toMutableList() - erstellt eine veränderbare Kopie
        // ============================================
        
        val newNote = Note(
            id = generateId(),
            title = title,
            content = content
        )
        
        val currentNotes = _notes.value ?: mutableListOf()
        currentNotes.add(0, newNote) // Neue Notiz oben einfügen
        
        _notes.value = currentNotes
        saveNotes()
    }
    
    /**
     * Löscht eine Notiz
     */
    fun deleteNote(noteId: Long) {
        // ============================================
        // KOTLIN: Lambda-Ausdrücke & Listen-Filter
        // ============================================
        // { it.id != noteId } - Lambda: für jedes Element (it), prüfe ob ID != noteId
        // .filter() - behält nur Elemente, für die der Lambda true zurückgibt
        // In Java: notes.stream().filter(n -> n.getId() != noteId).collect(...)
        // ============================================
        
        val currentNotes = _notes.value ?: return
        val updatedNotes = currentNotes.filter { it.id != noteId }.toMutableList()
        
        _notes.value = updatedNotes
        saveNotes()
    }
    
    /**
     * Speichert Notizen in SharedPreferences
     */
    private fun saveNotes() {
        val currentNotes = _notes.value ?: return
        
        // ============================================
        // KOTLIN: JSON Serialisierung mit Gson
        // ============================================
        // Gson wandelt Objekte in JSON um und zurück
        // typeToken ist nötig für generische Typen (List<Note>)
        // ============================================
        
        val jsonString = gson.toJson(currentNotes)
        sharedPreferences.edit().putString(NOTES_KEY, jsonString).apply()
    }
    
    /**
     * Lädt Notizen aus SharedPreferences
     */
    private fun loadNotes() {
        val jsonString = sharedPreferences.getString(NOTES_KEY, null)
        
        if (jsonString != null) {
            // ============================================
            // KOTLIN: TypeToken für generische Typen
            // ============================================
            // In Java: Type type = new TypeToken<List<Note>>(){}.getType();
            // ============================================
            
            val type = object : TypeToken<MutableList<Note>>() {}.type
            val loadedNotes = gson.fromJson<MutableList<Note>>(jsonString, type)
            
            _notes.value = loadedNotes ?: mutableListOf()
        }
    }
}

// ============================================
// JAVA-VERGLEICH (vereinfacht):
// ============================================
/*
public class NotesViewModel extends ViewModel {
    private MutableLiveData<List<Note>> notes = new MutableLiveData<>(new ArrayList<>());
    private SharedPreferences sharedPreferences;
    private Gson gson = new Gson();

    public LiveData<List<Note>> getNotes() { return notes; }

    public void addNote(String title, String content) {
        Note newNote = new Note(generateId(), title, content);
        List<Note> currentNotes = notes.getValue();
        if (currentNotes == null) currentNotes = new ArrayList<>();
        currentNotes.add(0, newNote);
        notes.setValue(currentNotes);
        saveNotes();
    }

    public void deleteNote(long noteId) {
        List<Note> currentNotes = notes.getValue();
        if (currentNotes == null) return;
        List<Note> updatedNotes = currentNotes.stream()
            .filter(n -> n.getId() != noteId)
            .collect(Collectors.toList());
        notes.setValue(updatedNotes);
        saveNotes();
    }

    public static long generateId() {
        return System.currentTimeMillis();
    }

    public void init(Context context) {
        sharedPreferences = context.getSharedPreferences("NotesPrefs", Context.MODE_PRIVATE);
        loadNotes();
    }

    private void saveNotes() { ... }
    private void loadNotes() { ... }
}
*/
// ============================================
