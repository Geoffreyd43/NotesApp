package com.example.notesapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.notesapp.data.Note
import com.example.notesapp.repository.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val notesRepository: NotesRepository
): ViewModel() {
    fun getNotes(): List<Note> {
        return notesRepository.getNotes()
    }
}
