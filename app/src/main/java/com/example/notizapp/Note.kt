package com.example.notizapp

// ============================================
// KOTLIN: Datenklasse (Data Class)
// ============================================
// In Java: public class Note { private String title; private String content; ... }
// + Getter/Setter + equals() + hashCode() + toString() - alles automatisch!
//
// Data Classes sind perfekt für einfache Datenhalter (wie Notizen)
//
// Wichtige Kotlin-Konzepte hier:
// - 'data class' - generiert automatisch nützliche Methoden
// - 'val' - immutable (wie final in Java) - kann nicht geändert werden
// - 'var' - mutable (kann geändert werden) - vermeiden für Datenklassen
// - Properties ohne Getter/Setter - Kotlin macht das automatisch
// ============================================

/**
 * Einfache Datenklasse für eine Notiz
 * @param title Der Titel der Notiz (z.B. "Einkaufsliste")
 * @param content Der Inhalt der Notiz (z.B. "Milch, Eier, Brot")
 */
data class Note(
    val id: Long,           // Eindeutige ID (für späteres Bearbeiten/Löschen)
    val title: String,      // Titel der Notiz
    val content: String     // Inhalt der Notiz
) {
    // ============================================
    // KOTLIN: Null-Sicherheit
    // ============================================
    // In Kotlin sind Typen standardmäßig NOT NULL
    // title: String = kann NICHT null sein
    // title: String? = kann null sein
    //
    // In Java: String title; (kann null sein)
    // In Kotlin: val title: String (kann NICHT null sein)
    // In Kotlin: val title: String? (kann null sein)
    // ============================================
}

// ============================================
// JAVA-VERGLEICH:
// ============================================
/*
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
    public boolean equals(Object o) { ... }
    @Override
    public int hashCode() { ... }
    @Override
    public String toString() { ... }
}
*/
// ============================================
