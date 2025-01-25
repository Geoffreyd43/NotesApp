package com.example.notesapp.repository

import com.example.notesapp.data.Note

class MockNotesRepository : NotesRepository {
    override fun getNotes(): List<Note> {
        return listOf(
            Note(
                title = "Todo",
                content = "* kick butt\n* take names"
            ),
            Note(
                title = "Shopping list",
                content = "Milk, eggs, cheese..."
            ),
            Note(
                title = "Passwords",
                content = "Silly human, I would never keep my passwords here ;)"
            )
        )
    }
}