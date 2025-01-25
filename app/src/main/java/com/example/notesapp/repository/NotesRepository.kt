package com.example.notesapp.repository

import com.example.notesapp.data.Note

interface NotesRepository {
    fun getNotes(): List<Note>
}