package com.example.notizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notizapp.ui.theme.NotizappTheme

// ============================================
// KOTLIN: MainActivity - Einstiegspunkt der App
// ============================================
// ComponentActivity ist die Basis für Compose-Aktivitäten
// setContent {} - setzt den Compose-Content für die Activity
// ============================================

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // ============================================
        // KOTLIN: setContent für Compose
        // ============================================
        // In Java: setContentView(R.layout.activity_main);
        // In Kotlin mit Compose: setContent { ... Compose-UI ... }
        // ============================================
        
        setContent {
            NotizappTheme {
                // ============================================
                // KOTLIN: Surface - Hintergrund der App
                // ============================================
                // Surface ist ein Container mit Material-Design-Hintergrund
                // ============================================
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // ============================================
                    // KOTLIN: App-Content
                    // ============================================
                    // Hier wird unsere NotesAppScreen aufgerufen
                    // ============================================
                    NotesAppScreen()
                }
            }
        }
    }
}

// ============================================
// KOTLIN: @Composable Funktionen
// ============================================
// @Composable - markiert eine Funktion als Compose-UI-Komponente
// Jede Composable-Funktion gibt UI-Elemente zurück
//
// Wichtige Regeln:
// 1. Composable-Funktionen müssen @Composable sein
// 2. Sie können andere @Composable-Funktionen aufrufen
// 3. Sie können nur innerhalb anderer @Composable-Funktionen aufgerufen werden
// 4. Sie können State halten (mit remember, mutableStateOf, etc.)
// ============================================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesAppScreen(viewModel: NotesViewModel = viewModel()) {
    
    // ============================================
    // KOTLIN: ViewModel mit Compose
    // ============================================
    // viewModel() - erstellt oder holt das ViewModel
    // observeAsState() - konvertiert LiveData zu Compose State
    // ============================================
    
    val context = androidx.compose.ui.platform.LocalContext.current
    
    // Initialisiere ViewModel mit Context
    // LaunchedEffect führt Side-Effekte einmalig aus (wie viewModel.init)
    LaunchedEffect(Unit) {
        viewModel.init(context)
    }
    
    val notes: MutableList<Note> by viewModel.notes.observeAsState(initial = mutableListOf())
    
    // ============================================
    // KOTLIN: State für den Dialog
    // ============================================
    // mutableStateOf - erstellt einen mutable State
    // remember - speichert den State über Recompositions hinweg
    // var (statt val) - weil wir den State ändern wollen
    // ============================================
    
    var showAddDialog by remember { mutableStateOf(false) }
    var newNoteTitle by remember { mutableStateOf("") }
    var newNoteContent by remember { mutableStateOf("") }
    
    // ============================================
    // KOTLIN: Scaffold - Material Design Layout
    // ============================================
    // Scaffold bietet Slots für:
    // - topBar (AppBar)
    // - bottomBar
    // - floatingActionButton
    // - content (Hauptinhalt)
    // ============================================
    
    Scaffold(
        topBar = {
            // ============================================
            // KOTLIN: App-Bar mit Titel
            // ============================================
            Text(
                text = "Meine Notizen",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
        },
        floatingActionButton = {
            // ============================================
            // KOTLIN: FloatingActionButton
            // ============================================
            // Öffnet den Dialog zum Hinzufügen einer Notiz
            // ============================================
            FloatingActionButton(
                onClick = { 
                    showAddDialog = true
                    newNoteTitle = ""
                    newNoteContent = ""
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Neue Notiz hinzufügen"
                )
            }
        }
    ) { paddingValues ->
        // ============================================
        // KOTLIN: Hauptinhalt - Notizen-Liste
        // ============================================
        // LazyColumn - recycelbare Liste (wie RecyclerView in XML)
        // items(notes) - für jedes Element in notes
        // ============================================
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            
            if (notes.isEmpty()) {
                // ============================================
                // KOTLIN: Leerer Zustand
                // ============================================
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Noch keine Notizen. Tippe auf + zum Hinzufügen!",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            } else {
                // ============================================
                // KOTLIN: LazyColumn für die Notizen-Liste
                // ============================================
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(notes) { currentNote ->
                        // ============================================
                        // KOTLIN: Notiz-Karte
                        // ============================================
                        // Jede Notiz wird als Card dargestellt
                        // ============================================
                        NoteCard(
                            note = currentNote,
                            onDelete = { viewModel.deleteNote(currentNote.id) }
                        )
                    }
                }
            }
        }
        
        // ============================================
        // KOTLIN: Dialog zum Hinzufügen einer Notiz
        // ============================================
        // if (showAddDialog) - bedingte UI
        // ============================================
        
        if (showAddDialog) {
            // ============================================
            // KOTLIN: AlertDialog-Alternative mit Box
            // ============================================
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Neue Notiz",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        
                        // ============================================
                        // KOTLIN: TextField für Titel
                        // ============================================
                        // value = newNoteTitle - der aktuelle Wert
                        // onValueChange = { newNoteTitle = it } - Update-Funktion
                        // ============================================
                        TextField(
                            value = newNoteTitle,
                            onValueChange = { newNoteTitle = it },
                            label = { Text("Titel") },
                            placeholder = { Text("z.B. Einkaufsliste") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        // ============================================
                        // KOTLIN: TextField für Inhalt
                        // ============================================
                        TextField(
                            value = newNoteContent,
                            onValueChange = { newNoteContent = it },
                            label = { Text("Inhalt") },
                            placeholder = { Text("z.B. Milch, Eier, Brot") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp),
                            singleLine = false
                        )
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        // ============================================
                        // KOTLIN: Buttons - Speichern & Abbrechen
                        // ============================================
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Button(
                                onClick = { showAddDialog = false },
                                modifier = Modifier.padding(end = 8.dp)
                            ) {
                                Text("Abbrechen")
                            }
                            
                            Button(
                                onClick = {
                                    if (newNoteTitle.isNotBlank()) {
                                        viewModel.addNote(newNoteTitle, newNoteContent)
                                        showAddDialog = false
                                    }
                                },
                                enabled = newNoteTitle.isNotBlank()
                            ) {
                                Text("Speichern")
                            }
                        }
                    }
                }
            }
        }
    }
}

// ============================================
// KOTLIN: NoteCard - Composable für eine einzelne Notiz
// ============================================
// @Composable Funktion für eine wiederverwendbare Komponente
// ============================================

@Composable
fun NoteCard(note: Note, onDelete: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        // ============================================
        // KOTLIN: onClick - Lambda-Funktion
        // ============================================
        // onClick = { ... } - wird ausgeführt, wenn geklickt wird
        // ============================================
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // ============================================
            // KOTLIN: Notiz-Titel (fett)
            // ============================================
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            // ============================================
            // KOTLIN: Notiz-Inhalt
            // ============================================
            Text(
                text = note.content,
                style = MaterialTheme.typography.bodyMedium
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // ============================================
            // KOTLIN: Lösch-Button
            // ============================================
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = onDelete
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Notiz löschen",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

// ============================================
// KOTLIN: Preview für Composable
// ============================================
// @Preview - zeigt die Komponente im Android Studio Preview an
// ============================================

@Preview(showBackground = true)
@Composable
fun NoteCardPreview() {
    NotizappTheme {
        NoteCard(
            note = Note(id = 1L, title = "Einkaufsliste", content = "Milch, Eier, Brot"),
            onDelete = {}
        )
    }
}

// ============================================
// JAVA-VERGLEICH (vereinfacht):
// ============================================
/*
MainActivity (Java mit XML):
- activity_main.xml mit RecyclerView
- NoteAdapter.java für die Liste
- OnClickListener für Buttons
- DialogFragment für das Hinzufügen

MainActivity (Java):
public class MainActivity extends AppCompatActivity {
    private NotesViewModel viewModel;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        viewModel = new ViewModelProvider(this).get(NotesViewModel.class);
        viewModel.init(this);
        
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        NoteAdapter adapter = new NoteAdapter(viewModel.getNotes().getValue(), note -> {
            viewModel.deleteNote(note.getId());
        });
        recyclerView.setAdapter(adapter);
        
        viewModel.getNotes().observe(this, notes -> {
            adapter.updateNotes(notes);
        });
        
        findViewById(R.id.fab).setOnClickListener(v -> {
            showAddNoteDialog();
        });
    }
    
    private void showAddNoteDialog() {
        // Dialog-Code...
    }
}
*/
// ============================================
