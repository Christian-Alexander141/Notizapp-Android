# Notizapp - Android Notizen-App mit Kotlin & Jetpack Compose

[![Kotlin](https://img.shields.io/badge/Kotlin-2.2.10-purple.svg?style=flat-square&logo=kotlin)](https://kotlinlang.org)
[![Android](https://img.shields.io/badge/Android-24+-green.svg?style=flat-square&logo=android)](https://developer.android.com)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-1.6.0-blue.svg?style=flat-square)](https://developer.android.com/jetpack/compose)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg?style=flat-square)](https://opensource.org/licenses/MIT)

**Ein einfaches Tutorial-Projekt, um Kotlin und Jetpack Compose zu lernen.**
Perfekt für Java-Entwickler, die auf Kotlin umsteigen wollen!

---

## 📌 Inhaltsverzeichnis

- [🎯 Projektbeschreibung](#-projektbeschreibung)
- [✨ Features](#-features)
- [🏗️ Architektur](#-architektur)
- [🚀 Vorraussetzungen](#-vorraussetzungen)
- [📦 Abhängigkeiten](#-abhängigkeiten)
- [🔧 Setup & Installation](#-setup--installation)
- [📱 App-Verwendung](#-app-verwendung)
- [💡 Kotlin für Java-Entwickler](#-kotlin-für-java-entwickler)
  - [Grundlagen](#grundlagen)
  - [Datenklassen (Data Classes)](#datenklassen-data-classes)
  - [Null-Sicherheit](#null-sicherheit)
  - [Funktionen vs. Methoden](#funktionen-vs-methoden)
  - [Listen & Collections](#listen--collections)
  - [Companion Objects](#companion-objects)
  - [Extension Functions](#extension-functions)
  - [Coroutines (Asynchronität)](#coroutines-asynchronität)
- [🎨 Jetpack Compose Grundlagen](#-jetpack-compose-grundlagen)
  - [State Management](#state-management)
  - [Composable Funktionen](#composable-funktionen)
  - [Layouts](#layouts)
  - [Modifiers](#modifiers)
  - [Listeners & Events](#listeners--events)
  - [Listen & LazyColumn](#listen--lazycolumn)
- [📊 Code-Struktur im Detail](#-code-struktur-im-detail)
  - [Note.kt](#notekt)
  - [NotesViewModel.kt](#notesviewmodelkt)
  - [MainActivity.kt](#mainactivitykt)
- [🔄 Fähigkeiten, die du lernst](#-fähigkeiten-die-du-lernst)
- [📈 Mögliche Erweiterungen](#-mögliche-erweiterungen)
- [🎓 Lernressourcen](#-lernressourcen)
- [🤝 Mitwirken](#-mitwirken)
- [📜 Lizenz](#-lizenz)

---

## 🎯 Projektbeschreibung

**Notizapp** ist eine einfache Android-Anwendung zum Verwalten von Notizen. Das Projekt wurde speziell als **Lern-Tutorial** entwickelt, um:

- **Kotlin-Grundlagen** zu verstehen (für Java-Entwickler)
- **Jetpack Compose** kennenzulernen (modernes UI-Framework)
- **MVVM-Architektur** in Android anzuwenden
- **Persistenz mit SharedPreferences** zu implementieren

Das Projekt ist **absichtlich einfach gehalten**, um die Konzepte klar zu vermitteln. Es ist der perfekte Einstieg für Entwickler, die von Java zu Kotlin wechseln oder Compose lernen möchten.

---

## ✨ Features

| Feature | Beschreibung | Implementiert |
|---------|--------------|---------------|
| 📝 Notizen erstellen | Notizen mit Titel und Inhalt hinzufügen | ✅ |
| 📋 Notizen anzeigen | Alle Notizen in einer scrollbaren Liste | ✅ |
| 🗑️ Notizen löschen | Notizen mit einem Klick entfernen | ✅ |
| 💾 Persistente Speicherung | Notizen bleiben nach App-Neustart erhalten | ✅ |
| 🎨 Modernes UI | Material Design 3 mit Jetpack Compose | ✅ |
| 📱 Responsive Design | Funktioniert auf allen Android-Geräten | ✅ |

---

## 🏗️ Architektur

```
┌─────────────────────────────────────────────────────────────┐
│                      UI Layer (Compose)                     │
│  ┌─────────────────┐  ┌─────────────────┐  ┌─────────────┐ │
│  │   MainActivity  │  │   NoteCard      │  │  Dialogs    │ │
│  │   (Compose)     │  │   (Composable)  │  │             │ │
│  └────────┬────────┘  └────────┬────────┘  └──────┬──────┘ │
│           │                    │                 │         │
│           ▼                    ▼                 ▼         │
└───────────┼────────────────────┼─────────────────┼─────────┘
            │                    │                 │
            ▼                    ▼                 ▼
┌─────────────────────────────────────────────────────────────┐
│                   Business Logic Layer                      │
│  ┌─────────────────────────────────────────────────────────┐ │
│  │                  NotesViewModel                         │ │
│  │  - addNote(title: String, content: String)              │ │
│  │  - deleteNote(noteId: Long)                              │ │
│  │  - loadNotes(): List<Note>                              │ │
│  │  - saveNotes()                                          │ │
│  └─────────────────────────────────────────────────────────┘ │
└───────────┬─────────────────────────────────────────────────┘
            │
            ▼
┌─────────────────────────────────────────────────────────────┐
│                     Data Layer                              │
│  ┌─────────────────┐  ┌───────────────────────────────────┐ │
│  │    Note.kt      │  │      SharedPreferences           │ │
│  │   (Data Class)  │  │  + Gson (JSON Serialization)     │ │
│  └─────────────────┘  └───────────────────────────────────┘ │
└─────────────────────────────────────────────────────────────┘
```

**Architektur-Pattern: MVVM (Model-View-ViewModel)**

- **Model**: `Note` Datenklasse
- **View**: Compose UI (`MainActivity`, `NoteCard`)
- **ViewModel**: `NotesViewModel` (Business Logic)
- **Repository**: Direkt in ViewModel (einfach gehalten für Tutorial)

---

## 🚀 Vorraussetzungen

- **Android Studio** (empfohlen: Arctic Fox 2020.3.1 oder neuer)
- **Kotlin Plugin** (automatisch installiert)
- **Java JDK 17** (oder neuer)
- **Android SDK** (API Level 24 oder höher)
- **Emulator** oder physisches Android-Gerät (API 24+)

---

## 📦 Abhängigkeiten

| Bibliothek | Version | Zweck |
|------------|---------|-------|
| `androidx.core:core-ktx` | 1.10.1 | Kotlin Extensions für Android |
| `androidx.lifecycle:lifecycle-runtime-ktx` | 2.6.1 | LiveData & ViewModel |
| `androidx.lifecycle:lifecycle-viewmodel-compose` | 2.8.7 | ViewModel für Compose |
| `androidx.lifecycle:lifecycle-runtime-compose` | 2.8.7 | LiveData für Compose |
| `androidx.lifecycle:lifecycle-livedata-ktx` | 2.8.7 | LiveData Kotlin Extensions |
| `androidx.compose.runtime:runtime-livedata` | 1.6.0 | LiveData in Compose |
| `androidx.activity:activity-compose` | 1.8.0 | Compose in Activities |
| `androidx.compose:compose-bom` | 2026.02.01 | Compose Bill of Materials |
| `androidx.compose.material3:material3` | 1.2.0 | Material Design 3 Components |
| `androidx.compose.material:material-icons-extended` | 1.6.0 | Material Icons |
| `com.google.code.gson:gson` | 2.10.1 | JSON Serialisierung |

---

## 🔧 Setup & Installation

### 1️⃣ Projekt klonen

```bash
# Repository klonen
git clone https://github.com/Christian-Alexander141/Notizapp-Android.git

# In den Projektordner wechseln
cd Notizapp-Android
```

### 2️⃣ Projekt in Android Studio öffnen

1. Android Studio starten
2. **File → Open...** wählen
3. Den Projektordner (`Notizapp-Android`) auswählen
4. Android Studio lädt das Projekt und synchronisiert Gradle

### 3️⃣ Emulator starten oder Gerät verbinden

**Option A: Emulator erstellen**
1. **Tools → Device Manager** öffnen
2. **Create Virtual Device** klicken
3. Ein Gerät auswählen (z.B. Pixel 6)
4. System Image: **Android 14 (API 34)** oder höher
5. Auf **Finish** klicken
6. Emulator starten

**Option B: Physisches Gerät verbinden**
1. USB-Debugging auf dem Gerät aktivieren
2. Gerät per USB mit dem Computer verbinden
3. In Android Studio: Gerät auswählen

### 4️⃣ App bauen und starten

```bash
# Debug-Version bauen
./gradlew assembleDebug

# App installieren (falls Gerät verbunden)
./gradlew installDebug
```

**Oder einfach in Android Studio:**
- ✅ **Run-Button (▶️)** klicken
- Gerät/Emulator auswählen
- App startet automatisch

---

## 📱 App-Verwendung

### 📝 Notiz erstellen

1. App starten
2. Auf den **➕ (Floating Action Button)** in der unteren rechten Ecke klicken
3. **Titel** eingeben (z.B. "Einkaufsliste")
4. **Inhalt** eingeben (z.B. "Milch, Eier, Brot, Butter")
5. Auf **Speichern** klicken

✅ Die Notiz erscheint oben in der Liste

### 📋 Notizen anzeigen

- Alle Notizen werden in einer **scrollbaren Liste** angezeigt
- Jede Notiz zeigt:
  - **Titel** (fett gedruckt)
  - **Inhalt** (normaler Text)
  - **Löschen-Button** (🗑️ Mülleimer-Icon)

### 🗑️ Notiz löschen

1. Auf den **🗑️-Button** der gewünschten Notiz klicken
2. Die Notiz wird sofort aus der Liste entfernt
3. Die Änderung wird **automatisch gespeichert**

### 💾 Persistenz testen

1. App schließen (komplett beenden)
2. App neu starten
3. ✅ Alle Notizen sind noch da!

---

## 💡 Kotlin für Java-Entwickler

### Grundlagen

| Konzept | Kotlin | Java | Erklärung |
|---------|--------|------|------------|
| **Klasse** | `class Person` | `public class Person {}` | Vereinfachte Syntax |
| **Property** | `val name: String` | `private String name;` + Getter | `val` = immutable (final) |
| **Mutable Property** | `var age: Int` | `private int age;` + Getter/Setter | `var` = veränderbar |
| **Konstruktor** | `class Person(val name: String)` | `public Person(String name) { this.name = name; }` | Primary Constructor |
| **Methode** | `fun greet(): String` | `public String greet()` | `fun` statt `public` |
| **Return-Type** | Nach Parametern | Vor Parametern | `fun add(a: Int, b: Int): Int` |
| **if-Expression** | `val max = if (a > b) a else b` | `int max = a > b ? a : b;` | if gibt Wert zurück |
| **for-Loop** | `for (item in list)` | `for (Item item : list)` | Vereinfachte Syntax |
| **while-Loop** | `while (condition)` | `while (condition)` | Gleich |
| **Switch** | `when (x) { 1 -> ... }` | `switch (x) { case 1: ... }` | `when` statt `switch` |

### Datenklassen (Data Classes)

**Kotlin:**
```kotlin
data class Note(
    val id: Long,
    val title: String,
    val content: String
)
```

**Java Äquivalent:**
```java
public class Note {
    private final long id;
    private final String title;
    private final String content;

    public Note(long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }

    @Override
    public boolean equals(Object o) { /* auto-generated */ }
    
    @Override
    public int hashCode() { /* auto-generated */ }
    
    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
```

**💡 Vorteile von Data Classes:**
- ✅ **Automatische Generierung** von `equals()`, `hashCode()`, `toString()`
- ✅ **Automatische Generierung** von `copy()` Methode
- ✅ **ComponentN-Funktionen** für Destructuring: `val (id, title, content) = note`
- ✅ **Viel weniger Boilerplate-Code**

### Null-Sicherheit

**Kotlin hat integrierte Null-Sicherheit!**

| Typ | Bedeutung | Java Äquivalent |
|-----|-----------|-----------------|
| `String` | **Nicht null** | Kein direktes Äquivalent |
| `String?` | **Kann null sein** | `String` |
| `String!!` | **Force non-null** (gefährlich!) | `String` mit NPE-Risiko |

**Beispiele:**

```kotlin
// ✅ SAFE - title kann NICHT null sein
val title: String = "Hallo"
// title = null // ❌ COMPILE ERROR!

// ✅ SAFE - title kann null sein
val title: String? = "Hallo"
title = null // ✅ OK

// ✅ SAFE CALL - wenn title null ist, gibt es null zurück
val length = title?.length

// ✅ Elvis Operator - Fallback wenn null
val length = title?.length ?: 0

// ❌ UNSAFE - wirft NPE wenn title null ist
val length = title!!.length
```

**Java-Vergleich:**
```java
// Java: Immer null-sicher manuell prüfen
String title = getTitle();
if (title != null) {
    int length = title.length();
}
```

### Funktionen vs. Methoden

**Kotlin:**
```kotlin
// Einfache Funktion
fun add(a: Int, b: Int): Int {
    return a + b
}

// Single-Expression Funktion (Return implizit)
fun add(a: Int, b: Int): Int = a + b

// Funktion mit Default-Parametern
fun greet(name: String = "Guest", greeting: String = "Hello"): String {
    return "$greeting, $name!"
}

// Aufruf mit Default-Werten
greet() // "Hello, Guest!"
greet("Alex") // "Hello, Alex!"
greet(greeting = "Hallo") // "Hallo, Guest!"
```

**Java:**
```java
// Java: Keine Default-Parameter
public int add(int a, int b) {
    return a + b;
}

// Überladung nötig für Default-Werte
public String greet() {
    return greet("Guest", "Hello");
}
public String greet(String name) {
    return greet(name, "Hello");
}
public String greet(String name, String greeting) {
    return greeting + ", " + name + "!";
}
```

### Listen & Collections

**Kotlin:**
```kotlin
// Unveränderliche Liste
val list = listOf("A", "B", "C")
// list.add("D") // ❌ COMPILE ERROR - listOf gibt unveränderliche Liste

// Veränderliche Liste
val mutableList = mutableListOf("A", "B", "C")
mutableList.add("D") // ✅ OK

// Map
val map = mapOf("key1" to "value1", "key2" to "value2")

// Map mit mutablen Werten
val mutableMap = mutableMapOf("key1" to "value1")

// Listen-Operationen (Funktional)
val numbers = listOf(1, 2, 3, 4, 5)
val even = numbers.filter { it % 2 == 0 } // [2, 4]
val doubled = numbers.map { it * 2 } // [2, 4, 6, 8, 10]
val sum = numbers.reduce { acc, i -> acc + i } // 15
val hasThree = numbers.any { it == 3 } // true
val allEven = numbers.all { it % 2 == 0 } // false

// forEach
numbers.forEach { println(it) }
```

**Java:**
```java
// Unveränderliche Liste
List<String> list = Arrays.asList("A", "B", "C");
// list.add("D"); // ❌ UnsupportedOperationException

// Veränderliche Liste
List<String> mutableList = new ArrayList<>(Arrays.asList("A", "B", "C"));
mutableList.add("D"); // ✅ OK

// Map
Map<String, String> map = Map.of("key1", "value1", "key2", "value2");

// Listen-Operationen (Stream API)
List<Integer> numbers = List.of(1, 2, 3, 4, 5);
List<Integer> even = numbers.stream()
    .filter(n -> n % 2 == 0)
    .collect(Collectors.toList());
```

### Companion Objects

**Kotlin:**
```kotlin
class MyClass {
    companion object {
        // Ähnlich wie static in Java
        const val PI = 3.14
        
        fun create(): MyClass {
            return MyClass()
        }
    }
}

// Verwendung
val pi = MyClass.PI
val instance = MyClass.create()
```

**Java:**
```java
public class MyClass {
    public static final double PI = 3.14;
    
    public static MyClass create() {
        return new MyClass();
    }
}

// Verwendung
double pi = MyClass.PI;
MyClass instance = MyClass.create();
```

**💡 Wichtig:** Companion Objects können auch **Interfaces implementieren**!

### Extension Functions

**Kotlin:**
```kotlin
// Füge eine neue Funktion zu String hinzu
fun String.addExclamation(): String {
    return "$this!"
}

// Füge eine neue Funktion zu Int hinzu
fun Int.isEven(): Boolean {
    return this % 2 == 0
}

// Verwendung
val result = "Hello".addExclamation() // "Hello!"
val isEven = 4.isEven() // true
```

**Java:**
```java
// Java: Nicht möglich! Muss Utility-Klasse erstellen
public class StringUtils {
    public static String addExclamation(String str) {
        return str + "!";
    }
}

// Verwendung
String result = StringUtils.addExclamation("Hello");
```

**💡 Vorteile:** Extension Functions ermöglichen sauberen, lesbaren Code ohne die ursprüngliche Klasse zu modifizieren!

### Coroutines (Asynchronität)

**Kotlin Coroutines** ersetzen Callbacks und Threads für asynchrone Programmierung.

**Beispiel: Netzwerkaufruf**

```kotlin
// ViewModel mit Coroutines
class MyViewModel : ViewModel() {
    private val _data = MutableLiveData<String>()
    val data: LiveData<String> = _data
    
    fun loadData() {
        viewModelScope.launch { // Startet eine Coroutine
            try {
                // Netzwerkaufruf (suspend function)
                val result = repository.fetchData()
                _data.value = result
            } catch (e: Exception) {
                // Fehlerbehandlung
                _data.value = "Error: ${e.message}"
            }
        }
    }
}
```

**Java-Vergleich:**
```java
// Java: Mit Callbacks
public class MyViewModel extends ViewModel {
    private MutableLiveData<String> data = new MutableLiveData<>();
    
    public void loadData() {
        repository.fetchData(new Callback<String>() {
            @Override
            public void onSuccess(String result) {
                data.setValue(result);
            }
            
            @Override
            public void onError(Exception e) {
                data.setValue("Error: " + e.getMessage());
            }
        });
    }
}
```

**💡 Vorteile von Coroutines:**
- ✅ **Sequentieller Code** statt Callback-Hell
- ✅ **Kein Thread-Management** nötig
- ✅ **Strukturierte Concurrency** (keine Memory Leaks)
- ✅ **Einfache Fehlerbehandlung** mit try-catch

---

## 🎨 Jetpack Compose Grundlagen

### State Management

**Compose ist deklarativ:** Die UI ist eine Funktion des States.

```kotlin
// State in Compose
@Composable
fun Counter() {
    // State Variable
    var count by remember { mutableStateOf(0) }
    
    Column {
        Text("Count: $count")
        
        Button(onClick = { count++ }) {
            Text("Increment")
        }
    }
}
```

**Wie es funktioniert:**
1. `remember` speichert den State über Recompositions
2. `mutableStateOf(0)` erstellt einen veränderbaren State
3. `var count by ...` verwendet Property Delegation
4. Wenn `count` geändert wird, wird die Funktion **neu aufgerufen** (Recomposition)
5. Nur die geänderten Teile werden **neu gezeichnet**

### Composable Funktionen

**Eine @Composable Funktion ist eine UI-Komponente:**

```kotlin
@Composable
fun Greeting(name: String) {
    Text(text = "Hello, $name!")
}

// Verwendung
@Composable
fun MyScreen() {
    Greeting(name = "Alex")
    Greeting(name = "Christian")
}
```

**Regeln für @Composable Funktionen:**
- ✅ Müssen mit `@Composable` annotiert sein
- ✅ Können andere `@Composable` Funktionen aufrufen
- ✅ Können nur innerhalb anderer `@Composable` Funktionen aufgerufen werden
- ✅ Können Parameter haben (aber keine komplexen Objekte als Parameter)
- ❌ **Dürfen keine Side-Effekte haben** (außer in `LaunchedEffect` oder `remember`)

### Layouts

**Compose bietet verschiedene Layout-Container:**

| Container | Beschreibung | Java XML Äquivalent |
|-----------|--------------|---------------------|
| `Column` | Vertikale Anordnung | `LinearLayout` (vertical) |
| `Row` | Horizontale Anordnung | `LinearLayout` (horizontal) |
| `Box` | Überlagerung | `FrameLayout` |
| `LazyColumn` | Scrollbare Liste | `RecyclerView` |
| `LazyRow` | Horizontale scrollbare Liste | `RecyclerView` (horizontal) |
| `ConstraintLayout` | Komplexe Layouts | `ConstraintLayout` |

**Beispiel: Column**
```kotlin
Column {
    Text("Erste Zeile")
    Text("Zweite Zeile")
    Button(onClick = {}) {
        Text("Button")
    }
}
```

**Beispiel: Row**
```kotlin
Row {
    Text("Links")
    Spacer(modifier = Modifier.width(16.dp))
    Text("Rechts")
}
```

**Beispiel: Box**
```kotlin
Box(modifier = Modifier.size(100.dp)) {
    Text("Hinten", modifier = Modifier.align(Alignment.TopStart))
    Text("Vorne", modifier = Modifier.align(Alignment.Center))
}
```

### Modifiers

**Modifiers sind das Herz von Compose-Layouts:**

```kotlin
Text(
    text = "Hello",
    modifier = Modifier
        .padding(16.dp)           // Innenabstand
        .fillMaxWidth()           // volle Breite
        .background(Color.Blue)   // Hintergrundfarbe
        .padding(8.dp)            // weiter Innenabstand
        .border(1.dp, Color.Black)// Rahmen
)
```

**Häufige Modifiers:**

| Modifier | Beschreibung |
|----------|--------------|
| `Modifier.padding(16.dp)` | Innenabstand |
| `Modifier.size(100.dp)` | Feste Größe |
| `Modifier.fillMaxWidth()` | Volle Breite |
| `Modifier.fillMaxSize()` | Volle Größe |
| `Modifier.width(100.dp)` | Feste Breite |
| `Modifier.height(100.dp)` | Feste Höhe |
| `Modifier.background(Color.Blue)` | Hintergrund |
| `Modifier.border(1.dp, Color.Black)` | Rahmen |
| `Modifier.clickable { ... }` | Klickbar |
| `Modifier.align(Alignment.Center)` | Ausrichtung |
| `Modifier.weight(1f)` | Gewichtung (wie in LinearLayout) |

### Listeners & Events

**Klick-Events:**

```kotlin
// Button Click
Button(onClick = { 
    // Code beim Klick
    println("Button geklickt!")
}) {
    Text("Klick mich")
}

// TextField Value Change
var text by remember { mutableStateOf("") }
TextField(
    value = text,
    onValueChange = { newText ->
        text = newText
    }
)

// IconButton Click
IconButton(onClick = { 
    // Code beim Klick
}) {
    Icon(Icons.Default.Add, contentDescription = "Hinzufügen")
}
```

**💡 Wichtig:** Event-Handler sind **Lambda-Funktionen**!

### Listen & LazyColumn

**LazyColumn ist das Compose-Äquivalent zu RecyclerView:**

```kotlin
// Liste von Notizen
val notes = listOf(
    Note(1, "Notiz 1", "Inhalt 1"),
    Note(2, "Notiz 2", "Inhalt 2"),
    Note(3, "Notiz 3", "Inhalt 3"),
)

// LazyColumn für effizientes Scrollen
LazyColumn {
    items(notes) { note ->
        NoteCard(note = note)
    }
}
```

**LazyColumn mit Index:**
```kotlin
LazyColumn {
    itemsIndexed(notes) { index, note ->
        Text("$index: ${note.title}")
    }
}
```

**LazyColumn mit Key (für stabile Identität):**
```kotlin
LazyColumn {
    items(notes, key = { note -> note.id }) { note ->
        NoteCard(note = note)
    }
}
```

**💡 Vorteile von LazyColumn:**
- ✅ **Recycelt** View-Holder (wie RecyclerView)
- ✅ **Lazy Loading** - nur sichtbare Items werden erstellt
- ✅ **Performance** - optimal für große Listen
- ✅ **Einfache API** - kein Adapter nötig

---

## 📊 Code-Struktur im Detail

### Note.kt

```kotlin
package com.example.notizapp

data class Note(
    val id: Long,           // Eindeutige ID
    val title: String,      // Titel (nicht null!)
    val content: String     // Inhalt (nicht null!)
)
```

**Wichtige Kotlin-Konzepte:**
- `data class` - Generiert automatisch `equals()`, `hashCode()`, `toString()`, `copy()`
- `val` - Unveränderliche Properties (wie `final` in Java)
- **Keine Null-Typen** - `String` kann nicht null sein (Null-Sicherheit!)

**Verwendung:**
```kotlin
val note = Note(id = 1, title = "Einkaufsliste", content = "Milch, Eier")
println(note.title) // "Einkaufsliste"

// copy() - erstellt eine modifizierte Kopie
val updatedNote = note.copy(title = "Neuer Titel")
```

---

### NotesViewModel.kt

```kotlin
class NotesViewModel : ViewModel() {
    private val _notes = MutableLiveData<MutableList<Note>>()
    val notes: LiveData<MutableList<Note>> = _notes
    
    fun addNote(title: String, content: String) { ... }
    fun deleteNote(noteId: Long) { ... }
    private fun saveNotes() { ... }
    private fun loadNotes() { ... }
    fun init(context: Context) { ... }
}
```

**Architektur:**
- **ViewModel** überlebt Configuration Changes (z.B. Screen Rotation)
- **LiveData** informiert die UI automatisch über Änderungen
- **Private Backing Property** (`_notes`) + öffentliche `notes` Property

**Wichtige Kotlin-Konzepte:**
- **Companion Object** - `generateId()` ist wie `static` in Java
- **Lambda in filter** - `{ it.id != noteId }` statt `n -> n.getId() != noteId`
- **Safe Call** - `currentNotes?.add(newNote)` prüft auf null
- **Gson** - JSON-Serialisierung für SharedPreferences

**Speicherung:**
- **SharedPreferences** - Einfache Key-Value-Speicherung
- **Gson** - Wandelt `List<Note>` in JSON um und zurück
- **TypeToken** - Für generische Typen in Gson

---

### MainActivity.kt

```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotizappTheme {
                Surface {
                    NotesAppScreen()
                }
            }
        }
    }
}

@Composable
fun NotesAppScreen(viewModel: NotesViewModel = viewModel()) {
    val context = LocalContext.current
    
    LaunchedEffect(Unit) {
        viewModel.init(context)
    }
    
    val notes by viewModel.notes.observeAsState(initial = mutableListOf())
    
    // UI-Code...
}
```

**Wichtige Compose-Konzepte:**
- `setContent { ... }` - Setzt den Compose-Content
- `viewModel()` - Erstellt oder holt das ViewModel
- `LaunchedEffect` - Führt Seiteneffekte einmalig aus
- `observeAsState()` - Konvertiert LiveData zu Compose State
- `by` - Property Delegation (für State)

**UI-Komponenten:**
- **Scaffold** - Material Design Layout mit AppBar, FAB, etc.
- **LazyColumn** - Scrollbare Liste
- **Card** - Material Design Card
- **TextField** - Eingabefeld
- **Button** - Standard-Button
- **FloatingActionButton** - FAB
- **IconButton** - Button mit Icon
- **Dialog** - Benutzerdefinierter Dialog (mit Card)

---

## 🔄 Fähigkeiten, die du lernst

### 🎯 Kotlin-Grundlagen
- [x] Datenklassen (Data Classes)
- [x] Null-Sicherheit
- [x] Properties (val vs var)
- [x] Funktionen & Parameter
- [x] Lambda-Ausdrücke
- [x] Collections & Listen-Operationen
- [x] Companion Objects
- [ ] Coroutines & Asynchronität
- [ ] Sealed Classes
- [ ] Data Classes & Destructuring
- [ ] Extension Functions
- [ ] Object Declarations (Singleton)

### 🎨 Jetpack Compose
- [x] @Composable Funktionen
- [x] State Management
- [x] remember & mutableStateOf
- [x] LaunchedEffect
- [x] Scaffold & Material Design
- [x] LazyColumn (Listen)
- [x] Modifier System
- [x] Event Handler
- [x] Conditional UI (if/else)
- [ ] Navigation
- [ ] Theming
- [ ] Animationen

### 🏗️ Android-Architektur
- [x] MVVM (Model-View-ViewModel)
- [x] ViewModel & LiveData
- [x] SharedPreferences
- [x] Gson (JSON Serialisierung)
- [ ] Room Database
- [ ] Repository Pattern
- [ ] Dependency Injection (Hilt/Koin)

---

## 📈 Mögliche Erweiterungen

Hier sind einige Ideen, wie du die App erweitern kannst:

### 🔹 Einfache Erweiterungen (für Anfänger)

| Feature | Beschreibung | Schwierigkeit |
|---------|--------------|---------------|
| ✏️ Notizen bearbeiten | Klick auf Notiz öffnet Bearbeitungsdialog | ⭐⭐ |
| 🔍 Suchfunktion | Notizen nach Titel oder Inhalt suchen | ⭐⭐ |
| 🎨 Farbige Notizen | Notizen mit verschiedenen Farben markieren | ⭐⭐ |
| 📅 Datum & Uhrzeit | Automatiksches Datum zu jeder Notiz | ⭐⭐ |
| 🏷️ Kategorien | Notizen in Kategorien gruppieren | ⭐⭐ |
| ⬆️ Sortieren | Notizen nach Titel, Datum sortieren | ⭐⭐ |

### 🔹 Mittel (für Fortgeschrittene)

| Feature | Beschreibung | Schwierigkeit |
|---------|--------------|---------------|
| 📱 Room Database | SharedPreferences durch Room ersetzen | ⭐⭐⭐ |
| 🔄 Drag & Drop | Notizen per Drag & Drop sortieren | ⭐⭐⭐ |
| 📤 Export/Import | Notizen als JSON exportieren/importieren | ⭐⭐⭐ |
| 🔒 Dark Mode | Automatischer Dark/Light Mode | ⭐⭐⭐ |
| 🌐 Web-Sync | Notizen mit Firebase syncen | ⭐⭐⭐ |
| 📷 Bilder hinzufügen | Bilder zu Notizen hinzufügen | ⭐⭐⭐ |

### 🔹 Fortgeschritten (für Experten)

| Feature | Beschreibung | Schwierigkeit |
|---------|--------------|---------------|
| 🚀 Jetpack Navigation | Mehrere Screens mit Navigation | ⭐⭐⭐⭐ |
| ⚡ Performance | LazyColumn optimieren | ⭐⭐⭐⭐ |
| 🎭 Animationen | Smooth Animationen beim Hinzufügen/Löschen | ⭐⭐⭐⭐ |
| 🔐 Biometrisch | App mit Fingerabdruck schützen | ⭐⭐⭐⭐ |
| ☁️ Cloud Sync | Notizen mit Google Drive syncen | ⭐⭐⭐⭐ |
| 📊 Statistiken | Anzahl Notizen, etc. anzeigen | ⭐⭐⭐⭐ |

---

## 🎓 Lernressourcen

### 📚 Offizielle Dokumentation

- [Kotlin Language Documentation](https://kotlinlang.org/docs/home.html)
- [Android Developer Guide](https://developer.android.com/guide)
- [Jetpack Compose Documentation](https://developer.android.com/jetpack/compose)
- [Android Architecture Guide](https://developer.android.com/jetpack/guide)

### 🎥 Video-Tutorials

- [Kotlin for Beginners (FreeCodeCamp)](https://www.youtube.com/watch?v=F9UC9DY-vIU)
- [Jetpack Compose Tutorial (Philip Lackner)](https://www.youtube.com/c/PhilipLackner)
- [Android MVVM Tutorial (Reso Coder)](https://www.youtube.com/c/ResoCoder)
- [Kotlin Coroutines (Android Developers)](https://www.youtube.com/watch?v=K8xzZ2l9724)

### 📖 Bücher

- [Kotlin in Action](https://www.manning.com/books/kotlin-in-action) - Dmitry Jemerov, Svetlana Isakova
- [Android Programming: The Big Nerd Ranch Guide](https://www.bignerdranch.com/books/android-programming/) - Bill Phillips, Chris Stewart
- [Head First Kotlin](https://www.oreilly.com/library/view/head-first-kotlin/9781491996683/) - Dawn Griffiths, David Griffiths

### 💬 Communities

- [Kotlin Slack](https://surveys.jetbrains.com/s3/kotlinlang-slack-sign-up)
- [Android Dev Reddit](https://www.reddit.com/r/androiddev/)
- [Stack Overflow - Kotlin](https://stackoverflow.com/questions/tagged/kotlin)
- [Stack Overflow - Android](https://stackoverflow.com/questions/tagged/android)

---

## 🤝 Mitwirken

Falls du Verbesserungen oder Erweiterungen vornehmen möchtest:

1. **Fork** das Repository
2. **Erstelle einen neuen Branch** (`git checkout -b feature/amazing-feature`)
3. **Commit** deine Änderungen (`git commit -m 'Add amazing feature'`)
4. **Push** zum Branch (`git push origin feature/amazing-feature`)
5. **Erstelle einen Pull Request**

---

## 📜 Lizenz

Dieses Projekt ist unter der **MIT License** lizenziert:

```
MIT License

Copyright (c) 2024 Christian-Alexander141

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

---

## 🙏 Danke

Ein großes Danke an:
- **JetBrains** für die Entwicklung von Kotlin
- **Google** für Android und Jetpack Compose
- **Die Android Community** für die tollen Tutorials und Ressourcen

---

## 📞 Kontakt

Falls du Fragen hast oder Feedback geben möchtest:
- **GitHub:** [Christian-Alexander141](https://github.com/Christian-Alexander141)
- **E-Mail:** (falls in deinem GitHub-Profil hinterlegt)

---

**Viel Spaß beim Lernen und Entwickeln!** 🚀

> "Programming is the art of telling another human what one wants the computer to do." - Donald Knuth
